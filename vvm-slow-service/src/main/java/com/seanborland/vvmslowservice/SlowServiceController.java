package com.seanborland.vvmslowservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class SlowServiceController {
    
    @GetMapping(value = "/slowService/{sleepTime}")
    public Mono<String> callSlowService(@PathVariable String sleepTime) {
        return Mono.just("DONE!")
                .delayElement(Duration.ofMillis(Integer.parseInt(sleepTime)));
    }
}
