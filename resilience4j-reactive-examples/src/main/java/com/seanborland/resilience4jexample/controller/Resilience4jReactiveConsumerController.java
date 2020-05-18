package com.seanborland.resilience4jexample.controller;

import com.seanborland.resilience4jexample.service.Resilience4jReactiveConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class Resilience4jReactiveConsumerController {
    
    private final Resilience4jReactiveConsumerService resilience4jReactiveConsumerService;
    
    public Resilience4jReactiveConsumerController(Resilience4jReactiveConsumerService resilience4jReactiveConsumerService) {
        this.resilience4jReactiveConsumerService = resilience4jReactiveConsumerService;
    }
    
    @GetMapping("/resilience4j-reactive-consumer")
    public Mono<String> callProducer() {
        
        return resilience4jReactiveConsumerService.callProducer();
    }
}
