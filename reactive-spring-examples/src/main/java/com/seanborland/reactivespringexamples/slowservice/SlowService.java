package com.seanborland.reactivespringexamples.slowservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/slowService")
public class SlowService {
    
    @GetMapping("/{timeout}")
    public String slowService(@PathVariable int timeout) throws InterruptedException {
        Thread.sleep(timeout);
        return "Done with timeout of: " + timeout;
    }
}
