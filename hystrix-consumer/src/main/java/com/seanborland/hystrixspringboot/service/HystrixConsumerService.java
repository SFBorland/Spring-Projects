package com.seanborland.hystrixspringboot.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class HystrixConsumerService {
    
    private final RestTemplate restTemplate;
    
    public HystrixConsumerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @HystrixCommand(fallbackMethod = "defaultName")
    public String callProducer() {
        log.debug("*** Inside callProducer ****");
        return restTemplate.getForObject("http://localhost:9090/producer", String.class);
    }
    
    public String defaultName() {
        
        log.error("xxx Inside Fallback xxx");
        return "Circuit Breaker Default Name";
    }
}
