//package com.seanborland.reactivespringexamples.circuitbreakerexample;
//
//import org.junit.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.reactive.function.client.ClientResponse;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//
//public class CircuitBreakerRunner {
//
//    @Test
//    public void alpha() {
//        System.out.println(makeWebClientCallExchange().block());
//    }
//
//    public Mono<String> makeWebClientCallExchange() {
//        return WebClient.create("http://localhost:9090/slowService/8000")
//                .get()
//                .exchange()
//                .flatMap(CircuitBreakerRunner::mapServiceResponse)
//                .timeout(Duration.ofSeconds(2))
//                .onErrorResume(error -> {
//                    if (error.toString().contains("TimeoutException")) {
//                        System.out.println("################### CIRCUIT BREAKER EXCEPTION: TIMEOUT " +
//                                "######################");
//                    }
//                    return Mono.just("ERROR");
//                });
//    }
//
//    public Mono<String> makeWebClientCallRetrieve() {
//        return WebClient.create("http://localhost:9090/slowService/8000")
//                .get()
//                .retrieve()
//                .onStatus(HttpStatus::isError, clientResponse -> {
//                    clientResponse.statusCode().
//                })
//                .timeout(Duration.ofSeconds(2))
//                .onErrorResume(error -> {
//                    if (error.toString().contains("TimeoutException")) {
//                        System.out.println("################### CIRCUIT BREAKER EXCEPTION: TIMEOUT " +
//                                "######################");
//                    }
//                    return Mono.just("ERROR");
//                });
//
//    }
//
//    private static Mono<String> mapServiceResponse(ClientResponse serviceResponse) {
//        if (!serviceResponse.statusCode().is2xxSuccessful()) {
//
//
//            if (serviceResponse.statusCode().equals(HttpStatus.NOT_FOUND)
//                    || serviceResponse.statusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)
//                    || serviceResponse.statusCode().equals(HttpStatus.GATEWAY_TIMEOUT)) {
//                System.out.println("NOT WHAT WE'RE LOOKING FOR");
//            }
//        }
//        return Mono.just("DONE");
//    }
//}
//
//
