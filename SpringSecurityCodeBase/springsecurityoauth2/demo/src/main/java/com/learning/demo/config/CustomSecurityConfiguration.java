package com.learning.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomSecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecuritySetting(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(req->req.anyRequest().authenticated()).
                oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}
