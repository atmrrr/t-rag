package com.rag.crm.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.beans.Transient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthControllerTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void test1(){

        for (Object key : redisTemplate.keys("*")) {
            System.out.println(key);
        }


    }

}