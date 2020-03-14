package com.seanborland.vvmwebflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class VvmWebfluxController {
    
    final SlowServiceClient slowServiceClient;
    
    @Autowired
    public VvmWebfluxController(SlowServiceClient slowServiceClient) {
        this.slowServiceClient = slowServiceClient;
    }
    
    @GetMapping(value = "/webflux/{sleepTime}")
    public Mono<String> callSlowService(@PathVariable int sleepTime) {
        return slowServiceClient.callSlowService(sleepTime);
    }
}
