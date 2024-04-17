package com.demo.springsecuritybasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class ContactController {
    @GetMapping("/contacts")
    public String getMyContactDetails(){
        return "Bank Contact details from DB";
    }
}
