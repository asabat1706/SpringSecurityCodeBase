package com.demo.springsecuritybasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {
    @GetMapping("/notices")
    public String getAllNotices(){
        return "Bank Notice details from DB";
    }
}
