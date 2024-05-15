package com.learning.demo.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StarterController {

    @GetMapping("/")
    public String accessSecuredResources(OAuth2AuthenticationToken token){
        System.out.println(token.getPrincipal());
        return "page.html";
    }
}
