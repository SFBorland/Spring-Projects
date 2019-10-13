package com.seanborland.simplewebclientconsumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class SimpleWebclientService {
    
    public Mono<String> callSlowProducer() {
        
        System.out.println("Inside slow producer service call: " + Thread.currentThread().getName());
        return WebClient.create("http://localhost:9090/slow-producer")
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .log();
    }
    
    public Mono<String> callVerySlowProducer() {
        
        System.out.println("Inside very slow producer service call: " + Thread.currentThread().getName());
        return WebClient.create("http://localhost:9090/very-slow-producer")
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .log();
    }
}
