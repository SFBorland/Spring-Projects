package com.seanborland.reactivespringexamples.controller;


import com.seanborland.reactivespringexamples.webclientexamples.OfferingRequest;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@RestController
public class MonoController {
    
//    @GetMapping("/getMono")
//    public Mono<String> getMono() {
//        return Mono.fromCallable(this::getMyName)
//                .map(name -> name = name + " Borland.")
//                .subscribeOn(Schedulers.elastic());
//    }
//
//    private String getMyName() throws InterruptedException {
//        System.out.println("Inside getMyName method!");
//        Thread.sleep(3000);
//        return "Sean";
//    }
    @GetMapping("/getMonoPost")
    public Mono<String> getMonoPost() {
        System.out.println("HERE");
        //return
        return WebClient.create("http://shiptst1.mesos.rccl.com")
                .post()
                .uri("/offering/v1/en/royal/mobile/offering")
                .body(Mono.just(new OfferingRequest()), OfferingRequest.class)
                .retrieve()
                .bodyToMono(String.class);
    }
    
    
//    @GetMapping("/getMonoGet")
//    public Mono<String> getMonoGet() {
//        try {
//            ErrorResponse2 errorResponse = WebClient.create("http://shiptst1.mesos.rccl.com")
//                    .get()
//                    .uri("/error-messages/v1/read/errors?locale=en&errorCode=no.results.found")
//                    .retrieve()
//                    //.onStatus(HttpStatus::isError, error -> Mono.error(new NegativeArraySizeException()))
//                    .bodyToMono(ErrorResponse2.class)
////                .doOnError(ex -> {
////                    //ex.printStackTrace();
////                    ErrorResponse errorResponse1 = new ErrorResponse();
////                    errorResponse1.setErrorCode("errorMessage.service.error");
////                })
//                    .block(Duration.ofSeconds(3));
//            //log.info("Response from error-message-service: " + errorResponse);
//        } catch (Exception e) {
//            e.printStackTrace();
//            ErrorResponse2 errorResponse = new ErrorResponse2();
//            errorResponse.setErrorCode("ERROR SERVICE FAILED US");
//            System.out.println(errorResponse);
//        }
//        return null;
//    }
    
//    @Data
//    class ErrorResponse2 {
//
//        String errorCode;
//        String errorTitle;
//        String errorMessage;
//    }
    
}
