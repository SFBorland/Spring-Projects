package com.seanborland.reactivespringexamples.webclientexamples;

import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class WebClientPostExample {
    
    @Test
    public void postCallToEpo() {
        
        WebClient.create("http://shiptst1.mesos.rccl.com")
                .post()
                .uri("/offering/v1/en/royal/mobile/offering")
                .body(Mono.just(new OfferingRequest()), OfferingRequest.class)
                .retrieve()
                .bodyToMono(String.class)
                .log()
                .block(Duration.ofSeconds(20));
    }
}
