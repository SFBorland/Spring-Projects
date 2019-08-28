package com.seanborland.hystrixspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class HystrixSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixSpringBootApplication.class, args);
    }

}
