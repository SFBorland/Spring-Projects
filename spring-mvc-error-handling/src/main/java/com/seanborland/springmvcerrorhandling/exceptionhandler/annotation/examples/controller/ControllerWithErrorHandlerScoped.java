package com.seanborland.springmvcerrorhandling.exceptionhandler.annotation.examples.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ControllerWithErrorHandlerScoped {
    
    @GetMapping("/errorControllerScope")
    public String getScopeError() {
        System.out.println("getErrorOne");
        throw new IllegalArgumentException();
    }
    
    /**
     * This will overwrite the global ExceptionHandler defined in the other class.
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public String scopeLevelException() {
        log.error(">>> Scope level error <<<");
        return "I can return a specific error if I needed to for just this method.";
    }
}
