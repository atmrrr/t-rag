package com.mcp.tragrag.call;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "t-rag-crm")
public interface UserServiceCall {
    @GetMapping(value = "/user/test")
    String test();
}