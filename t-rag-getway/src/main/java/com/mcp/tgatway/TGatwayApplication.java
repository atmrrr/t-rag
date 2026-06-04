package com.mcp.tgatway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class TGatwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TGatwayApplication.class, args);
    }

    /**
     * route 就是判断将请求路由到哪一个 url 下面
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){

        return builder.routes()
                .route(r -> r.path("/user/*")
                        .uri("lb://t-rag-crm"))
                .build();

    }


}
