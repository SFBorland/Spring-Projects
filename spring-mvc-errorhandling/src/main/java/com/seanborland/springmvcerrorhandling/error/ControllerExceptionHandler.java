package com.seanborland.springmvcerrorhandling.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(IllegalArgumentException.class)
    private String handleErrorOne() {
        log.error(">>> ERROR HANDLER CALLED <<<");
        return "handleErrorOne called";
    }
    
    /**
     * You can't have two exceptionHandlers that are for the same Exception.class or else you get the below Ambigous
     * method error thrown when you run the application.
     *
     * Factory method 'handlerExceptionResolver' threw exception; nested exception is java.lang.IllegalStateException:
     * Ambiguous @ExceptionHandler method mapped for [class java.lang.IllegalArgumentException]:
     * {private java.lang.String com.seanborland.springmvcerrorhandling.error.ControllerExceptionHandler.handleErrorOne(),
     * private java.lang.String com.seanborland.springmvcerrorhandling.error.ControllerExceptionHandler.handleErrorTwo()}
     * @return
     */
    //@ExceptionHandler(IllegalArgumentException.class)
    //private String handleErrorTwo() {
    //    log.error(">>> ERROR HANDLER CALLED 2???<<<");
    //    return "handleErrorOne called";
    //}
}
