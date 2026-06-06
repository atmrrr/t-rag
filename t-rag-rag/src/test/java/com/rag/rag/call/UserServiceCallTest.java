package com.rag.rag.call;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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