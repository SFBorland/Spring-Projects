package com.seanborland.reactivespringexamples.controllerexamples.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
@Slf4j
public class ZipAsyncController {
    
    @GetMapping("/async")
    public Mono<Tuple2<String, String>> getResult() {
        
        return callOne().zipWith(callTwo());
    }
    
    @GetMapping("/async/{delayTime}")
    public Mono<Tuple2<String, String>> getResultWithSleep(@PathVariable int delayTime) {
        
        return Mono.zip(getWithDelayOne(delayTime), getWithDelayTwo(delayTime));
    }
    
    private Mono<String> callOne() {
        
        log.debug("*** Inside getOne ****");
        return WebClient.create("http://localhost:9090/producer")
                .get()
                .retrieve()
                .bodyToMono(String.class);
    }
    
    private Mono<String> callTwo() {
        
        log.debug("*** Inside getTwo ****");
        return WebClient.create("http://localhost:9090/producer")
                .get()
                .retrieve()
                .bodyToMono(String.class);
    }
    
    private Mono<String> getWithDelayOne(int delayTime) {
        return Mono.just("one").delayElement(Duration.ofSeconds(delayTime));
    }
    
    private Mono<String> getWithDelayTwo(long delayTime) {
        return Mono.just("two").delayElement(Duration.ofSeconds(delayTime));
    }
}
