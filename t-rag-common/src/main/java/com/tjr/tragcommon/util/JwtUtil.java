package com.tjr.tragcommon.util;

import com.tjr.tragcommon.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
@Component
public class JwtUtil {

    @Autowired
    private JwtConfig jwtConfig;

    public  String getToken(String userName, String orgId, String deptId, String role, List<String> promisees){

        Instant instant = Instant.now();
        Instant expire = instant.plus(1L, ChronoUnit.SECONDS);
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("orgId", orgId);
        map.put("deptId", deptId);
        map.put("role", role);
        map.put("promisees", promisees);

        return Jwts.builder()
                .issuer(jwtConfig.getIssuer())
                .id(UUID.randomUUID().toString())
                .issuedAt(Date.from(instant))
                .expiration(Date.from(expire))
                .claims(map)
                .signWith(getKey(jwtConfig.getSecret()))
                .compact();

    }

    private SecretKey getKey(String secrete){
        //使用 base64 解码密钥
        byte[] decode = Decoders.BASE64.decode(secrete);
        //将解码数据返回为 key 类型
        return Keys.hmacShaKeyFor(decode);

    }


    private Claims parseToken(String token){

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(getKey(jwtConfig.getSecret()))
                    .build()
                    .parseSignedClaims(token);
            // Claims 是一个 json 映射包括传入的 claims、id、issuer 等信息都可以在这里获取到
            return claimsJws.getPayload();
        }catch (Exception e){
            log.error("token 解析失败：{}", e.getMessage());
            throw e;
        }
    }


}
