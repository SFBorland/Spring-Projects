package com.seanborland.ehcachespringboot.controller;

import com.seanborland.ehcachespringboot.service.NumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/number")
public class NumberController {
    
    private final NumberService numberService;
    
    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }
    
    @GetMapping(path = "/square/{number}")
    public String getSquare(@PathVariable Long number) {
        log.info("call numberService to square {}", number);
        return "Square of " + number + " = " + numberService.square(number);
    }
}
