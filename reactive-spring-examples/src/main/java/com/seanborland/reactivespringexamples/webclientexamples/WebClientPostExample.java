//package com.seanborland.reactivespringexamples.webclientexamples;
//
//import org.junit.Test;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//
//public class WebClientPostExample {
//
//
////    public static void main(String[] args) {
////    OfferingRequest offeringRequest = new OfferingRequest();
////        WebClient.create("http://shiptst1.mesos.rccl.com")
////                .post()
////                .uri("/offering/v1/en/royal/mobile/offering")
////                .body(Mono.just(offeringRequest), OfferingRequest.class)
////                .retrieve()
////                .bodyToMono(String.class)
////                .log()
////                .block(Duration.ofSeconds(20));
////    }
//
//}
