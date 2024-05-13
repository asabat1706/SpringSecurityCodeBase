package com.demo.springsecuritybasic.config;

import com.demo.springsecuritybasic.model.Customer;
import com.demo.springsecuritybasic.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SecureBankAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<Customer> customer = customerRepository.findByCustomerName(authentication.getName());
        if(!customer.isEmpty()){
            if(passwordEncoder.matches(authentication.getCredentials().toString(), customer.get().getPassword())){
                List<GrantedAuthority> roles = new ArrayList<>();
                SimpleGrantedAuthority role = new SimpleGrantedAuthority(customer.get().getRole());
                roles.add(role);
                return new UsernamePasswordAuthenticationToken(customer.get().getCustomerName(),
                        customer.get().getPassword(), roles);
            }else{
                throw new RuntimeException("Invalid credantials");
            }

        }else{
            throw new RuntimeException("Customer details not found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

