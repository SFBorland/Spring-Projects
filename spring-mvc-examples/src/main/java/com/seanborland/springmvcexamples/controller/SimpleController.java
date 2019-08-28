package com.seanborland.springmvcexamples.controller;

import com.seanborland.springmvcexamples.service.SampleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class SimpleController {

//    @GetMapping("/getMonoPost")
//    public Mono<String> getMonoPost() {
//
//        //DO NOT call block from a controller, just return the mono/flux.
//        return WebClient.create("http://shiptst1.mesos.rccl.com")
//                .post()
//                .uri("/offering/v1/en/royal/mobile/offering")
//                .body(Mono.just(new OfferingRequest()), OfferingRequest.class)
//                .retrieve()
//                .bodyToMono(String.class);
//    }
    private final SampleService sampleService;
    
    @Autowired
    public SimpleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }
    
    @GetMapping(value = "/test/{ex}/sean")
    public void alpha(Request ex) {
//        System.out.println(sampleService.callSomething());
        
        System.out.println(ex);
    }
}

@Data
class Request {
    
    private String ex;
//    public String getEx() {
//        return ex;
//    }
//
//    public void setEx(String ex) {
//        this.ex = ex;
//    }
    
    
    
}
