package com.demo.springsecuritybasic.controller;

import com.demo.springsecuritybasic.model.Customer;
import com.demo.springsecuritybasic.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class ContactController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/contacts")
    public List<Customer>  getMyContactDetails(){

        return customerRepository.findAll();

    }
}
