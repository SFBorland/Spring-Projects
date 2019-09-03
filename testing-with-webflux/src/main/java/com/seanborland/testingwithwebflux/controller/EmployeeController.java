package com.seanborland.testingwithwebflux.controller;

import com.seanborland.testingwithwebflux.model.Employee;
import com.seanborland.testingwithwebflux.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class EmployeeController {
    
    private EmployeeService employeeService;
    
    @GetMapping(value = "/employees/{id}")
    public Mono<Employee> getEmployeeById(@PathVariable("id") int id) {
        return employeeService.getEmployeeById(id);
    }
    
    @PostMapping(value = "/employees", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Employee> createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }
    
    @DeleteMapping(value = "/employees/{id}")
    public Mono<String> deleteEmployeeById(@PathVariable("id") int id) {
        return employeeService.deleteEmployeeById(id);
    }
}
