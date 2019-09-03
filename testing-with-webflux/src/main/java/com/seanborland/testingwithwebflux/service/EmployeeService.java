package com.seanborland.testingwithwebflux.service;

import com.seanborland.testingwithwebflux.model.Employee;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    
    Mono<Employee> getEmployeeById(int id);
    
    Mono<Employee> createEmployee(Employee employee);
    
    Mono<String> deleteEmployeeById(int id);
}
