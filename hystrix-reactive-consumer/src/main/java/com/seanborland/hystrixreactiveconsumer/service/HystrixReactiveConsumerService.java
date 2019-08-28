package com.seanborland.hystrixreactiveconsumer.service;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
                .fallback(defaultMethod())
                .commandName("defaultMethod")
                .toMono();
    }
    
    private Mono<String> webClientCall() {
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
