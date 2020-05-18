package com.seanborland.service;

import java.util.List;

import com.seanborland.model.Customer;

public interface CustomerService {
    
    List<Customer> findAll();
}
