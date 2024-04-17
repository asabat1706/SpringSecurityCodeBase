package com.demo.springsecuritybasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/welcome")
    public String getWelcomeMessage(){
        return "Welcome to basic application";
    }
}
