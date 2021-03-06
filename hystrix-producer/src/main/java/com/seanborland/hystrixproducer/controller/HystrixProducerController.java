package com.seanborland.hystrixproducer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HystrixProducerController {
    
    @GetMapping("/producer")
    public String alpha() throws InterruptedException {
        
        log.info("*** producer called ***");
        Thread.sleep(6000);
        log.info("*** producer completed ***");
        return "Sean";
    }
}
