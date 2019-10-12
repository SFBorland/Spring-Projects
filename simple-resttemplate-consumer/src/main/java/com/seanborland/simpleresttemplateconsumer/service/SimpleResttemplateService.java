package com.seanborland.simpleresttemplateconsumer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SimpleResttemplateService {
    
    public String callSlowConsumer() {
    
        System.out.println("Inside slow consumer");
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:9090/slow-producer", String.class);
    }
    
    public String callVerySlowConsumer() {
        
        System.out.println("Inside very slow consumer");
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:9090/very-slow-producer", String.class);
    }
}
