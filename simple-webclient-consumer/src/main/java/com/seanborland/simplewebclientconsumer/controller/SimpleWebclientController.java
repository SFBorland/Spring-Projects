package com.seanborland.simplewebclientconsumer.controller;

import com.seanborland.simplewebclientconsumer.service.SimpleWebclientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class SimpleWebclientController {
    
    private SimpleWebclientService simpleWebclientService;
    
    @Autowired
    public SimpleWebclientController(SimpleWebclientService simpleWebclientService) {
        this.simpleWebclientService = simpleWebclientService;
    }
    
    @GetMapping("/reactive-consumer")
    public Flux<String> callSlowProducerTwo() {
        
        System.out.println("Controller Thread: " + Thread.currentThread().getName());
        
        System.out.println("Calling Very Slow Producer");
        Mono<String> resultMono2 = simpleWebclientService.callVerySlowProducer();//.subscribeOn(Schedulers.elastic());
        System.out.println("after Very Slow Producer call");
        
        System.out.println("Calling Slow Producer");
        Mono<String> resultMono = simpleWebclientService.callSlowProducer();//.subscribeOn(Schedulers.elastic());
        System.out.println("after Slow Producer call");
        
        return resultMono.mergeWith(resultMono2);
    }
}
