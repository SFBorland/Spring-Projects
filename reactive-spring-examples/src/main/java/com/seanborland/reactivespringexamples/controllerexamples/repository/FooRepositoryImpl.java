package com.seanborland.reactivespringexamples.controllerexamples.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class FooRepositoryImpl implements FooRepository {

    WebClient webClient;
    
    @Override
    public Mono<String> getBar() {
        
        log.info("Repo.getBar() START");
        
        return clientCall();//.subscribe();
        
//        log.info("Repo.getBar() END");
//        return "BarRepository Result!";
    }
    
    Mono<String> clientCall() {
        return WebClient.create("http://www.google.com")
                .get()
                .retrieve()
                .bodyToMono(String.class);
                
    }
}
