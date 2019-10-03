package com.seanborland.reactivespringexamples.controllerexamples.controller;

import com.seanborland.reactivespringexamples.controllerexamples.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FooController {
    
    private FooService fooService;
    
    @Autowired
    public FooController(FooService fooService) {
        this.fooService = fooService;
    }
    
    @GetMapping(value = "/foo")
    public Mono<String> alpha() {
        return fooService.getBar();
    }
}
