package com.tjr.tragcommon.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class JwtUtil {

    private static final String secrete = "oxZETUFaWrbtn2MKlW9qH5MjH8F8S3H8kM4ZCAEyLNw=";

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
                .issuer("t-rag")
                .id(UUID.randomUUID().toString())
                .issuedAt(Date.from(instant))
                .expiration(Date.from(expire))
                .claims(map)
                .signWith(getKey(secrete))
                .compact();

    }

    private SecretKey getKey(String secrete){
        //使用 base64 解码密钥
        byte[] decode = Decoders.BASE64.decode(secrete);
        //将解码数据返回为 key 类型
        return Keys.hmacShaKeyFor(decode);

    }

    private void validateToken(String token){

    }

    private Claims parseToken(String token){

        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(getKey(secrete))
                .build()
                .parseSignedClaims(token);
        // Claims 是一个 json 映射包括传入的 claims、id、issuer 等信息都可以在这里获取到
        return claimsJws.getPayload();
    }

    public static void main(String[] args) {

//        //生成密钥
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        //使用 base64 编码
//        String secret = Encoders.BASE64.encode(key.getEncoded());
//        System.out.println(secret);

        JwtUtil jwtUtil = new JwtUtil();
//        String token = jwtUtil.getToken("小明", "0001", "0001", "admin", Collections.singletonList("admin"));
//
//        System.out.println(token);


        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0LXJhZyIsImp0aSI6ImNhYmIzM2I0LWEwZTItNGNhNi1iY2NiLWYxNjIxNmU1MTZmNyIsImlhdCI6MTc4MDIzMjg5MywiZXhwIjoxNzgwMjMyODk0LCJyb2xlIjoiYWRtaW4iLCJkZXB0SWQiOiIwMDAxIiwidXNlck5hbWUiOiLlsI_mmI4iLCJvcmdJZCI6IjAwMDEiLCJwcm9taXNlZXMiOlsiYWRtaW4iXX0.7Jqyjmu3sw5qPWeOte6s7fCIjk80Helt4Ikqsq4Q0xM";
        Claims claims = jwtUtil.parseToken(token);
        System.out.println(claims.get("userName"));

    }

}
