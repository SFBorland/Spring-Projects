package com.seanborland.slowservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slowService")
public class SlowController {
    
    @GetMapping("/{timeout}")
    public String slowService(@PathVariable long timeout) throws InterruptedException {
        Thread.sleep(timeout);
        return "Done with timeout of: " + timeout;
    }
}
