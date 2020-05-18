package com.seanborland.vvmwebflux;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SlowServiceClient {
    
    public Mono<String> callSlowService(int sleepTime) {
        return WebClient.create("http://localhost:8082/slowService/" + sleepTime)
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .log();
    }
}
