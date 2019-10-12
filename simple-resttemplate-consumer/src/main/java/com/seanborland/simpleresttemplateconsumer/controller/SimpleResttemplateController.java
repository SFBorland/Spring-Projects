package com.seanborland.simpleresttemplateconsumer.controller;

import com.seanborland.simpleresttemplateconsumer.service.SimpleResttemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleResttemplateController {
    
    private SimpleResttemplateService simpleResttemplateService;
    
    @Autowired
    public SimpleResttemplateController(SimpleResttemplateService simpleResttemplateService) {
        this.simpleResttemplateService = simpleResttemplateService;
    }
    
    @GetMapping("/resttemplate-consumer")
    public String callSlowConsumer() {
        System.out.println("Controller Thread: " + Thread.currentThread().getName());
        System.out.println("Before RestTemplateCall 1");
        String result = simpleResttemplateService.callSlowConsumer();
        System.out.println("after call 1");
    
        System.out.println("Before RestTemplateCall 2");
        String result2 = simpleResttemplateService.callSlowConsumer();
        System.out.println("after call 2");
        
        System.out.println("Controller Thread after call: " + Thread.currentThread().getName());
        
        return result + result2;
    }
}
