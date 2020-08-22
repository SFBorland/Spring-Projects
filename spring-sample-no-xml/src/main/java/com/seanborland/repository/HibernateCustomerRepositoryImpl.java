package com.seanborland.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.seanborland.model.Customer;

//Since there are 3 beans that implement CustomerRepository, this tells Spring to always use this one.
@Primary
//** Stereotyping: This annotation serves as a specialization of @Component, allowing for implementation classes to be
//auto-detected through classpath scanning.
@Repository("hibernateRepository")
public class HibernateCustomerRepositoryImpl implements CustomerRepository {
    
    //** This is for the app.properties. The value in the prop file gets substituted here.
    @Value("${dbUsername}")
    public String dbUsername;
    
    @Override
    public List<Customer> findAll() {
        System.out.println("Value located in the app.properties file: " + dbUsername);
        List<Customer> customers = new ArrayList<>();
        
        Customer customer = new Customer();
        
        customer.setFirstname("Hibernate");
        customer.setLastname("Impl");
        
        customers.add(customer);
        
        return customers;
    }
}
