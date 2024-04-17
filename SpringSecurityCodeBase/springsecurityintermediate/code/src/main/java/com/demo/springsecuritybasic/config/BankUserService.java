package com.demo.springsecuritybasic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankUserService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Optional<Customer> customerOptional = customerRepository.findByCustomerName(username);
        if(customerOptional.isEmpty()){
            throw new RuntimeException("Customer Details not found for name: "+ username);
        }
        Customer customer = customerOptional.get();
        authorities.add(new SimpleGrantedAuthority(customer.getRole()));
        return new User(customer.getCustomerName(), customer.getPassword(), authorities);
    }
}
