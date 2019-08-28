package com.seanborland.hystrixspringboot.controller;

import com.seanborland.hystrixspringboot.service.HystrixConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HystrixConsumerController {
    
    private HystrixConsumerService hystrixConsumerService;
    
    @Autowired
    public HystrixConsumerController(HystrixConsumerService hystrixConsumerService) {
        this.hystrixConsumerService = hystrixConsumerService;
    }
    
    @GetMapping("/consumer")
    public String alpha() {
        
        //log.info("*** consumer called ***");
        return hystrixConsumerService.callProducer();
    }
}
