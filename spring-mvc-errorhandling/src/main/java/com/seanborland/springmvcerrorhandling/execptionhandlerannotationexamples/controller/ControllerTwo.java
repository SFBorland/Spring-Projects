package com.seanborland.springmvcerrorhandling.execptionhandlerannotationexamples.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * This is just to show that a second Controller class will get picked up by the ExceptionHandler.
 */
@RestController
public class ControllerTwo {
    
    @GetMapping("/error4")
    public String getErrorFour() {
        System.out.println("getErrorFour");
        throw new IllegalArgumentException();
    }
    
    @SneakyThrows
    @GetMapping("/error5")
    public String getErrorFive() {
        System.out.println("getErrorFive");
        throw new IOException();
    }
}
