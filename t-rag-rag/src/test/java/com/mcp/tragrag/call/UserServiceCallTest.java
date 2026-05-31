package com.mcp.tragrag.call;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceCallTest {

    @Autowired
    private UserServiceCall userServiceCall;

    @Test
    public void test(){
        String test = userServiceCall.test();
        System.out.println(test);
    }


}