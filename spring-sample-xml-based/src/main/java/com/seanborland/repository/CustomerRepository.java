package com.seanborland.repository;

import java.util.List;

import com.seanborland.model.Customer;

public interface CustomerRepository {

    List<Customer> findAll();
}
