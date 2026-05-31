package com.mcp.tragcrm.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthController {


    @PostMapping("/login")
    public Object login(){

        return "ok";
    }


}
