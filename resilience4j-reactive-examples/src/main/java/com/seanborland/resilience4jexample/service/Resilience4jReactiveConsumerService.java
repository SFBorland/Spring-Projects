package com.seanborland.resilience4jexample.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.ratelimiter.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class Resilience4jReactiveConsumerService {
    
    CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("Resilience4jReactiveConsumerService");
    RateLimiter rateLimiter = RateLimiter.ofDefaults("Resilience4jReactiveConsumerService");
    
    public Mono<String> callProducer() {
        
        return null;
    }
    
    private Mono<String> webClientCall() {
        //log.debug("*** Inside webClientCall ****");
        return WebClient.create("http://localhost:9090/producer")
                .get()
                .exchange()
                .doOnError(throwable -> log.error("ERROR IN CALL!!!!!!"))
                .flatMap(response -> {
                    log.debug(response.statusCode().toString());
                    return response.bodyToMono(String.class);
                })
                .compose(rateLimiter.executeRunnable(););
    }
    
    private Mono<String> defaultMethod(Throwable r) {
        
        //System.out.println("***" + r.getCause().toString());
        System.out.println(r.getClass().toString());
        //log.error("xxx Inside Fallback xxx");
        return Mono.just("Circuit breaker name.").map(result -> {
            log.error("CIRCUIT BLAH: " + r.getMessage());
            
            return result + " - ADDED IN FALLBACK!!";
        });
    }
}

/*
   CircuitBreaker circuitBreaker = CircuitBreaker.of("circuit", CircuitBreakerConfig.ofDefaults())

webClient.post()
        .uri("/test")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(requestBody)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new CustomException()))
            .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {})
            .transform(CircuitBreakerOperator.of(circuitBreaker))
            .onErrorResume(ex -> doFallback());
 */
