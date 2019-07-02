package com.seanborland.reactivespringexamples.controller;


import com.seanborland.reactivespringexamples.webclientexamples.OfferingRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class MonoController {
    
    @GetMapping("/getMonoPost")
    public Mono<String> getMonoPost() {
        
        return WebClient.create("http://shiptst1.mesos.rccl.com")
                .post()
                .uri("/offering/v1/en/royal/mobile/offering")
                .body(Mono.just(new OfferingRequest()), OfferingRequest.class)
                .retrieve()
                .bodyToMono(String.class);
    }
}
