package com.seanborland.springmvcexamples.controller;

import com.seanborland.springmvcexamples.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    
    private final SampleService sampleService;
    
    @Autowired
    public SimpleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }
    
    @GetMapping(value = "/")
    public void alpha() {
    
    }
}
