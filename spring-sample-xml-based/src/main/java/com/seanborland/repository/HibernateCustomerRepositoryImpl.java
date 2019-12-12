package com.seanborland.repository;

import java.util.ArrayList;
import java.util.List;

import com.seanborland.model.Customer;

public class HibernateCustomerRepositoryImpl implements CustomerRepository {

    @Override
    public List<Customer> findAll() {
        
        Customer customer = new Customer();
        customer.setFirstname("Sean");
        customer.setLastname("Borland");

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        return customers;
    }
}
