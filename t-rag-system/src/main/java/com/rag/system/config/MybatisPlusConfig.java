package com.rag.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisSqlLogInterceptor mybatisSqlLogInterceptor() {
        return new MybatisSqlLogInterceptor();
    }
}