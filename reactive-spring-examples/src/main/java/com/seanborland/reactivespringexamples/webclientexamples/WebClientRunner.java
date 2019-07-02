//package com.seanborland.reactivespringexamples.webclientexamples;
//
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//import java.util.logging.ErrorManager;
//
////@Slf4j
//public class WebClientRunner {
////
////    @Test
////    public void basicRestCallUsingWebClientAndBlock() {
////
////        ErrorResponse errorResponse = WebClient.create("http://shiptst1.mesos.rccl.com")
////                .get()
////                .uri("/error-messages/v1/read/errors?locale=en&errorCode=no.results.found")
////                .retrieve()
////                .bodyToMono(ErrorResponse.class)
////                .block();
////
////        //log.info("Response from error-message-service: " + errorResponse);
////    }
//
//    //@Test
//    public static void main(String[] args) {
//        try {
//
//        ErrorResponse errorResponse = WebClient.create("http://shiptst1.mesos.rccl.com")
//                .get()
//                .uri("/error-messages/v12/read/errors?locale=en&errorCode=no.results.found")
//                .retrieve()
//                //.onStatus(HttpStatus::isError, error -> Mono.error(new NegativeArraySizeException()))
//                .bodyToMono(ErrorResponse.class)
////                .doOnError(ex -> {
////                    //ex.printStackTrace();
////                    ErrorResponse errorResponse1 = new ErrorResponse();
////                    errorResponse1.setErrorCode("errorMessage.service.error");
////                })
//                .block(Duration.ofSeconds(3));
//        //log.info("Response from error-message-service: " + errorResponse);
//        } catch (Exception e) {
//            ErrorResponse errorResponse = new ErrorResponse();
//            errorResponse.setErrorCode("ERROR SERVICE FAILED US");
//            System.out.println(errorResponse);
//        }
//
//    }
//}
//
//@Data
//class ErrorResponse {
//
//    String errorCode;
//    String errorTitle;
//    String errorMessage;
//}
