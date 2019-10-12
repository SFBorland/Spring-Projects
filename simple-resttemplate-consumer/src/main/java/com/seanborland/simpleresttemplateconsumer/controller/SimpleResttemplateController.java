package com.seanborland.simpleresttemplateconsumer.controller;

import com.seanborland.simpleresttemplateconsumer.service.SimpleResttemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@RestController
public class SimpleResttemplateController {
    
    private SimpleResttemplateService simpleResttemplateService;
    
    @Autowired
    public SimpleResttemplateController(SimpleResttemplateService simpleResttemplateService) {
        this.simpleResttemplateService = simpleResttemplateService;
    }
    
    @GetMapping("/resttemplate-consumer")
    
    public String callSlowConsumer() {
    
        Instant start = Instant.now();
        System.out.println("Before very slow RestTemplate Call: " + Thread.currentThread().getName());
        String result = simpleResttemplateService.callVerySlowConsumer();
        System.out.println("After very slow RestTemplate Call");
    
        System.out.println("Before slow RestTemplate Call: " + Thread.currentThread().getName());
        String result2 = simpleResttemplateService.callSlowConsumer();
        System.out.println("After slow RestTemplate Call");
        Instant finish = Instant.now();
    
        System.out.println(result + " and " + result2);
        
        return "Time taken: " + Duration.between(start, finish).getSeconds() + "s";
    }
}
