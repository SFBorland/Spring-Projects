package com.seanborland.springmvcecommercesimple.controller;

import com.seanborland.springmvcecommercesimple.model.customer.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController("/customer")
public class CustomerController {
    
    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return Collections.emptyList();
    }
    
    @GetMapping("/{uuid}")
    public Customer getCustomerById(@PathVariable UUID uuid) {
        return new Customer();
    }
    
    @GetMapping("/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        return new Customer();
    }
    
    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        return new Customer();
    }
    
    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return new Customer();
    }
}
