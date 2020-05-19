package com.seanborland.reactivespringexamples.controllerexamples.controller;

import com.seanborland.reactivespringexamples.controllerexamples.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FooController {
    
    private FooService fooService;
    
    @Autowired
    public FooController(FooService fooService) {
        this.fooService = fooService;
    }
    
    @GetMapping(value = "/foo")
    public Mono<String> alpha() {
        return fooService.getBar();
    }
    
    @GetMapping(value = "/bar")
    public Mono<List<String>> bravo() {
        return doThing();
    }
    
    private Mono<List<String>> doThing() {
        List<String> myList = Arrays.asList("1", "2", "3", "4", "5", "6", "7");
        
        return Flux.fromIterable(myList)
                .flatMap(value -> callSlowGithub())
                .collect(Collectors.toList());
    }
    
    public Mono<String> callSlowGithub() {
        System.out.println("callSlowGithub called in thread: " + Thread.currentThread().getName());
        return WebClient.create("http://slowwly.robertomurray.co.uk/delay/3000/url/http://www.github.com")
                .get()
                .retrieve()
                .bodyToMono(String.class);
    }
}
