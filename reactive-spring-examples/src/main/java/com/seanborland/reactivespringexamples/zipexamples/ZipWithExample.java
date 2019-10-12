package com.seanborland.reactivespringexamples.zipexamples;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

public class ZipWithExample {
    
    @Test
    public void alpha() {
        Mono.just(getOne())
                .zipWith(Mono.just(getTwo())).subscribe(System.out::println);
    }
    
    private String getOne() {
    
        try {
            Thread.sleep(6000);
            System.out.println("one done!");
        } catch (InterruptedException e) {
        
        }
        return "one";
    }
    
    private String getTwo() {
        
        try {
            Thread.sleep(6000);
            System.out.println("two done!");
        } catch (InterruptedException e) {
        
        }
        return "two";
    }
}

