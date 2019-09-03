package com.seanborland.hystrixreactiveconsumer.service;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.netflix.hystrix.HystrixCommands;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class HystrixReactiveConsumerService {
    
    public Mono<String> callProducer() {
        
        log.debug("*** Inside callProducer ****");
        return HystrixCommands.from(webClientCall())
//                .commandProperties(HystrixCommandProperties.Setter()
//                        .withCircuitBreakerErrorThresholdPercentage(50)
//                        .withCircuitBreakerRequestVolumeThreshold(10))
                .fallback(defaultMethod())
                .commandName("defaultMethod")
                .toMono();
    }
    
    private Mono<String> webClientCall() {
        log.debug("*** Inside webClientCall ****");
        return WebClient.create("http://localhost:9090/producer")
                .get()
                .retrieve()
                .bodyToMono(String.class);
    }
    
    private Mono<String> defaultMethod() {
        
        log.error("xxx Inside Fallback xxx");
        return Mono.just("Circuit breaker name.");
    }
}

/*
* hystrixWebClient.exchangeCommandBuilder()
    .withCommandName(bankURL)
    .withErrorThresholdPercentage(50)
    .withRequestVolumeThreshold(5)
    .withSleepWindowInMilliseconds(5000)
    .withExecutionTimeoutInMilliseconds(1000)
    .exchange(request)
    .then(response -> response.bodyToMono(Quotation.class));
* */
