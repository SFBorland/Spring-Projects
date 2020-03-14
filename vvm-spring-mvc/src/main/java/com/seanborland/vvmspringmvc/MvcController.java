package com.seanborland.vvmspringmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MvcController {
    
    final SlowServiceClient slowServiceClient;
    
    @Autowired
    public MvcController(SlowServiceClient slowServiceClient) {
        this.slowServiceClient = slowServiceClient;
    }
    
    @GetMapping(value = "/mvc/{sleepTime}")
    public String callSlowService(@PathVariable int sleepTime) {
        return slowServiceClient.callSlowService(sleepTime);
    }
}
