package com.seanborland.springmvcerrorhandling.exceptionhandler.annotation.examples.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ControllerOne {
    
    @GetMapping("/error1")
    public String getErrorOne() {
        System.out.println("getErrorOne");
        throw new IllegalArgumentException();
    }
    
    @GetMapping("/error2")
    public String getErrorTwo() {
        System.out.println("getErrorTwo");
        throw new IllegalArgumentException();
    }
    
    /**
     * This should not get picked up by the ExceptionHandler because it doesn't throw the specified error.
     */
    @GetMapping("/error3")
    public String getErrorThree() {
        System.out.println("getErrorThree");
        throw new IndexOutOfBoundsException();
    }
    //TODO: how to exclude a controller from the advice.
}
