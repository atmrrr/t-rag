package com.mcp.tgatway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
public class TGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private AuthConfig authConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        List<String> whiteList = authConfig.getWhiteList();
        ServerHttpRequest request = exchange.getRequest();

        String path = request.getPath().value();
        //如果是白名单接口不做处理
        if (whiteList.contains(path)){

        }else {
            //不是白名单接口需要做权限校验处理

        }


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
