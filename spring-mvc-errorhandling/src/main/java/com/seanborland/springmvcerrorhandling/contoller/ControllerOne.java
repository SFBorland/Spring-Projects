package com.seanborland.springmvcerrorhandling.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ControllerOne {
    
    @GetMapping("/error1")
    public String getErrorOne() {
        System.out.println("HI");
        throw new IllegalArgumentException();
    }
    
    @GetMapping("/error2")
    public String getErrorTwo() {
        System.out.println("HI 2");
        throw new IllegalArgumentException();
    }
    
    //TODO: how to exclude a controller from the advice and make a controller that has scope defined error handling
    // w/i that controller.
}
