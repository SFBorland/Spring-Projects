package com.seanborland.springmvcerrorhandling.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTwo {
    @GetMapping("/error3")
    public String getErrorTwo() {
        System.out.println("HI 3");
        throw new IllegalArgumentException();
    }
}
