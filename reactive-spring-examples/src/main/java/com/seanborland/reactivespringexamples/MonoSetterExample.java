package com.seanborland.reactivespringexamples;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoSetterExample {
    
    @Test
    public void callableMono() throws InterruptedException {
        
        System.out.println("callableMono started...");
        
        Mono<String> monoResult = Mono.fromCallable(() -> {
            System.out.println("Inside callable mono.");
            
            Thread.sleep(3000);
            
            System.out.println("Inside callable, sleep done!");
            
            return "SEAN";
        }).subscribeOn(Schedulers.elastic());
        
        Person person = new Person();
        monoResult.map(result -> {
            person.setName(result);
            return "z";
        }).subscribe();
    
        System.out.println("NAME: " + person.getName());
        System.out.println();
        System.out.println("outside of fromCallable");
        
        Thread.sleep(4000);
        System.out.println("NAME: " + person.getName());
        System.out.println("after thread.sleep");
    }
}

@Data
class Person {
    String name;
}
