package com.seanborland.reactivespringexamples.controllerexamples.repository;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.List;


class UseBuilder {
    
    private WebClient webClient;
    
    public UseBuilder(String url) {
        webClient = WebClient.builder().baseUrl(url).build();
    }
    
    
    public Mono<String> getBar() {
        
        return clientCall();
    }
    
    Mono<String> clientCall() {
        
        return webClient.get()
                .retrieve()
                .bodyToMono(String.class);
    }
}
