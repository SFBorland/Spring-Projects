package com.seanborland.reactivespringexamples.webclientexamples;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WebClientRunner {
    
    @Test
    public void basicRestCallUsingWebClientAndBlock() {
        
        ErrorResponse errorResponse = WebClient.create("http://shiptst1.mesos.rccl.com")
                .get()
                .uri("/error-messages/v1/read/errors?locale=en&errorCode=no.results.found")
                .retrieve()
                .bodyToMono(ErrorResponse.class)
                .block();
        
        log.info("Response from error-message-service: " + errorResponse);
    }
    
    @Test
    public void basicRestCallUsingWebClientAndBlockAndTimeout() {
        
        ErrorResponse errorResponse = WebClient.create("http://shiptst1.mesos.rccl.com")
                .get()
                .uri("/error-messages/v1/read/errors?locale=en&errorCode=no.results.found")
                .retrieve()
                .bodyToMono(ErrorResponse.class)
                .block(Duration.ofSeconds(3));
        
        log.info("Response from error-message-service: " + errorResponse);
    }
}

@Data
class ErrorResponse {
    
    String errorCode;
    String errorTitle;
    String errorMessage;
}
