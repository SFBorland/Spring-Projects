package com.seanborland.springmvcerrorhandling.exceptionhandler.annotation.examples.errorhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    
    /**
     * This ExceptionHandler will handle all Controllers that throw IllegalArgumentException that don't have a more
     * granular scoped @ExceptionHandler(...) that handles IllegalArgumentException.
     *
     * NOTE about the need for @ResponseBody:
     * Annotation that indicates a method return value should be bound to the web response body.
     * Supported for annotated handler methods.
     * Basically, I was getting 404 instead of 200 w/ the return value of this method but when I define the
     * @ ExceptionHandler(...) w/i the controller itself I was getting the 200 and return value. The reason is that the
     * controller implicitly has the ResponseBody.
     */
    @ResponseBody
    @ExceptionHandler({IllegalArgumentException.class, IOException.class})
    public String handleException(Exception exception) {
        
        if (exception instanceof IllegalArgumentException) {
            log.error("IllegalArgumentException thrown");
        } else if (exception instanceof IOException) {
            log.error("IOException thrown");
        }
        log.error(">>> EXCEPTION HANDLER CALLED <<<");
        return "handleException called";
    }
    
    /**
     * You can't have two exceptionHandlers that are for the same Exception.class or else you get the below Ambigous
     * method error thrown when you run the application.
     *
     * Factory method 'handlerExceptionResolver' threw exception; nested exception is java.lang.IllegalStateException:
     * Ambiguous @ExceptionHandler method mapped for [class java.lang.IllegalArgumentException]:
     * {private java.lang.String com.seanborland.springmvcerrorhandling.execptionhandlerannotationexamples.errorhandler.ControllerExceptionHandler.handleErrorOne(),
     * private java.lang.String com.seanborland.springmvcerrorhandling.execptionhandlerannotationexamples.errorhandler.ControllerExceptionHandler.handleErrorTwo()}
     *
     */
    //@ExceptionHandler(IllegalArgumentException.class)
    //private String handleErrorTwo() {
    //    log.error(">>> ERROR HANDLER CALLED 2???<<<");
    //    return "handleErrorOne called";
    //}
}
