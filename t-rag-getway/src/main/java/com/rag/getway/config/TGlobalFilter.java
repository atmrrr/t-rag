package com.rag.getway.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.rag.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.rag.common.common.instances.JwtRequestHeader.*;

@Configuration
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TGlobalFilter implements GlobalFilter, Ordered {

    private final AuthConfig authConfig;
    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        List<String> whiteList = authConfig.getWhiteList();
        ServerHttpRequest request = exchange.getRequest();
        //远程服务器 ip
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        HttpMethod method = request.getMethod();
        //访问路径
        String path = request.getPath().value();
        log.info("系统接受请求：远程ip {} , 请求方法 {} , 请求路径 {}", remoteAddress, method, path);
        //访问参数
        if (method.equals(HttpMethod.POST)){
            Flux<DataBuffer> body = request.getBody();
            log.info("请求体内容 {}", body);
        }

        //如果是白名单接口不做处理
        if (whiteList.contains(path)){

            log.info("请求路径 {} 无需进行登录校验", path);

        }else {
            //不是白名单接口需要做权限校验处理
            log.info("请求路径 {} 需要进行登录校验", path);
            //1.从请求头中拿到 token 信息
            String token = request.
                    getHeaders().
                    getFirst(HttpHeaders.AUTHORIZATION);

            if (StrUtil.isBlank(token) || !token.startsWith("Bearer ")){
                return unauthorized(exchange, "未登录");
            }
            //2.解析 token
            //拿到用户的基本信息，然后添加到请求头里面，具体的服务，会从请求头中拿到基本信息并保存到 threadLocal 中
            try {
                token = token.substring(7);
                Claims claims = jwtUtil.parseToken(token);
                ServerWebExchange serverWebExchange = addUserInfo(exchange, claims);
                return chain.filter(serverWebExchange);

            }catch (Exception e){
                log.info("token 解析失败 {}",e.getMessage());
                e.printStackTrace();
                String errorMsg = "";
                errorMsg = e.getCause() == null? e.getMessage():e.getCause().getMessage();
                return unauthorized(exchange, errorMsg);
            }

        }


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
    //无法获取到正确的 token 信息，返回异常的 响应信息
    private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        String body = """
                {
                  "code": 401,
                  "message": "%s",
                  "data": null
                }
                """.formatted(message);

        DataBuffer buffer = response.bufferFactory()
                .wrap(body.getBytes(StandardCharsets.UTF_8));

        return response.writeWith(Mono.just(buffer));
    }

    //将用户信息添加到请求头中
    private ServerWebExchange  addUserInfo(ServerWebExchange exchange, Claims claims){

        Object userId = claims.get("userId");
        Object tenantId = claims.get("tenantId");
        Object deptId = claims.get("deptId");
        Object role = claims.get("role");

        //注意 webflux 的 exchange.getRequest() 是一个只读请求头，不能 添加新的请求头会报错
        ServerHttpRequest request = exchange.getRequest().mutate()
                .headers((headers -> {

                    headers.set(JWT_USER_ID, String.valueOf(userId));
                    headers.set(JWT_TENANT_ID, String.valueOf(tenantId));
                    headers.set(JWT_DEPT_ID, String.valueOf(deptId));
                    headers.set(JWT_ROLE_ID, String.valueOf(role));
                }))
                .build();

        return exchange.mutate()
                .request(request)
                .build();

    }

}
