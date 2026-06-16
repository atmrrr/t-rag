package com.rag.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rag.system.mapper")
public class TRagSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TRagSystemApplication.class, args);
    }

}
