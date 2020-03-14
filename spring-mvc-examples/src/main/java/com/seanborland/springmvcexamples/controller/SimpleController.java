package com.seanborland.springmvcexamples.controller;

import com.seanborland.springmvcexamples.service.SampleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private static int count = 0;
    
    @Autowired
    public SimpleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }
    
    @GetMapping(value = "/test/{ex}/sean")
    public void alpha(Request ex) throws InterruptedException {
//        System.out.println(sampleService.callSomething());
        
        System.out.println(count++);
        Thread.sleep(5000);
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
