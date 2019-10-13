package com.seanborland.hystrixreactiveconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class HystrixReactiveConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixReactiveConsumerApplication.class, args);
    }

}
