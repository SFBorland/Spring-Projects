package com.seanborland.hystrixreactiveconsumer.service;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.HystrixCommands;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.logging.Level;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import org.springframework.cloud.netflix.hystrix.HystrixCommands;
//import org.springframework.cloud.netflix.hystrix.HystrixCommands;

@Slf4j
@Service
//@EnableHystrix
public class HystrixReactiveConsumerService {
    
    public Mono<String> callProducer() {
        
        //log.debug("*** Inside callProducer ****");
        return HystrixCommands.from(webClientCall())
//                .commandProperties(HystrixCommandProperties.Setter()
//                        .withCircuitBreakerSleepWindowInMilliseconds(5000)
//                        .withCircuitBreakerErrorThresholdPercentage(50)
//                        .withCircuitBreakerRequestVolumeThreshold(10))
                .fallback(this::defaultMethod)
                .commandName("defaultMethod")
                .toMono();
    }

//    public Mono<String> callProducer() {
//
//        log.debug("*** Inside callProducer ****");
//        return HystrixCommands.from(webClientCall())
//                .commandProperties(HystrixCommandProperties.Setter()
//                        .withCircuitBreakerErrorThresholdPercentage(50)
//                        .withCircuitBreakerRequestVolumeThreshold(10))
//                .fallback(defaultMethod())
//                .commandName("defaultMethod")
//                .toMono();
//    }
    
    private Mono<String> webClientCall() {
        //log.debug("*** Inside webClientCall ****");
        return WebClient.create("http://localhost:9090/producer")
                .get()
                .exchange()
                .doOnError(throwable -> log.error("ERROR IN CALL!!!!!!"))
                .flatMap(response -> {
                    log.debug(response.statusCode().toString());
                    return response.bodyToMono(String.class);
                });
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
* hystrixWebClient.exchangeCommandBuilder()
    .withCommandName(bankURL)
    .withErrorThresholdPercentage(50)
    .withRequestVolumeThreshold(5)
    .withSleepWindowInMilliseconds(5000)
    .withExecutionTimeoutInMilliseconds(1000)
    .exchange(request)
    .then(response -> response.bodyToMono(Quotation.class));
* */
