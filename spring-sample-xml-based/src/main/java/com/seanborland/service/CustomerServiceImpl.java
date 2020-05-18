package com.seanborland.service;

import java.util.List;

import com.seanborland.model.Customer;
import com.seanborland.repository.CustomerRepository;

public class CustomerServiceImpl implements CustomerService {
    
    // ** The object was no longer hard coded. Now this can be used many times
    //    all that needs to be configured is the applicationContext.xml file.
    private CustomerRepository customerRepository;
    
    public CustomerServiceImpl() {
    }
    
    // ** This is using "Constructor Injection"
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
    
    // ** This is using setter injection, the bean will use the type "CustomerRepository" to autowire these two beans
    //    together.
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
