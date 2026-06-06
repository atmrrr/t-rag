package com.rag.crm.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthController {


    @PostMapping("/doLogin")
    public Object login(@RequestParam("usernName") String userName, @RequestParam("password")String password){

        return "ok";
    }


    @GetMapping("/getLoginId")
    public Object getLoginId(){

        return null;
    }


}
