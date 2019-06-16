package com.seanborland.reactivespringexamples.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class MonoController {
    
    @GetMapping("/getMono")
    public Mono<String> getMono() {
        return Mono.fromCallable(this::getMyName)
                .map(name -> name = name + " Borland.")
                .subscribeOn(Schedulers.elastic());
    }
    
    private String getMyName() throws InterruptedException {
        System.out.println("Inside getMyName method!");
        Thread.sleep(3000);
        return "Sean";
    }
}
