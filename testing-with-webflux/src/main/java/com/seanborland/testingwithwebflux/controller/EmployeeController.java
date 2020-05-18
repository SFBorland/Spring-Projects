package com.seanborland.testingwithwebflux.controller;

import com.seanborland.testingwithwebflux.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController {
    
    @PostMapping(value = "/employees/{id}/test")
    public Mono<Employee> getByReactive(@RequestBody Employee employee) {

        return Mono.just(employee);
    }
    
    @GetMapping(value = "/sean")
    public Mono<String> getByReactiveForDoc() {
        
        return Mono.just("I'm Sean.");
    }
//    @PostMapping(value = "/employees/{id}/test")
//    public Employee getByServlet(@RequestBody Employee employee) {
//
//        return employee;
//    }
}
