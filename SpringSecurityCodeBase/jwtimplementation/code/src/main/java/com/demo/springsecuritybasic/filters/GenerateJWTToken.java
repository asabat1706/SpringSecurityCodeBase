package com.demo.springsecuritybasic.filters;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class GenerateJWTToken extends OncePerRequestFilter {

    private final String jwt_secret_key="jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";

    @Value("${spring.security.jwt-header}")
    private String jwt_header_value="authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecretKey secretKey = Keys.hmacShaKeyFor(jwt_secret_key.getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder().issuer("Secure Bank").subject("JWT Token")
                .claim("username", authentication.getName())
                .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 500000000))
                .signWith(secretKey).compact();
        response.setHeader(jwt_header_value, jwt);

        filterChain.doFilter(request, response);
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authSet = new HashSet<>();
        authorities.forEach(auth->{
            authSet.add(auth.getAuthority());
        });
        return String.join(",", authSet);
    }

    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().contains("/public/register");
    }

}
