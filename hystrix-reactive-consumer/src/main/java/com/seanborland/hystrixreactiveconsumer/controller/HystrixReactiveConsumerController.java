package com.seanborland.hystrixreactiveconsumer.controller;

import com.seanborland.hystrixreactiveconsumer.service.HystrixReactiveConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class HystrixReactiveConsumerController {
    
    HystrixReactiveConsumerService hystrixReactiveConsumerService;
    
    public HystrixReactiveConsumerController(HystrixReactiveConsumerService hystrixReactiveConsumerService) {
        this.hystrixReactiveConsumerService = hystrixReactiveConsumerService;
    }
    
    @GetMapping("/reactive-consumer")
    public Mono<String> alpha() {
        return hystrixReactiveConsumerService.callProducer();
    }
}
