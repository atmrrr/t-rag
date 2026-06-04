package com.mcp.tragcrm.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthController {


    @PostMapping("/doLogin")
    public Object login(@RequestParam("usernName") String userName, @RequestParam("password")String password){

        StpUtil.login(userName);
        return "ok";
    }


    @GetMapping("/getLoginId")
    public Object getLoginId(){

        Object loginId = StpUtil.getLoginId();
        return loginId;
    }


}
