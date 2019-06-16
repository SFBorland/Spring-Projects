package com.seanborland.reactivespringexamples;

import lombok.Data;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoSetterExample {
    
    @Test
    public void callableMono() throws InterruptedException {
        
        System.out.println("callableMono started...");
        
        Person person = new Person();
        
        Mono.fromCallable(() -> {
            System.out.println("Inside callable mono.");
            
            Thread.sleep(3000);
            
            System.out.println("Inside callable, sleep done!");
            
            return "SEAN";
        })
                .subscribeOn(Schedulers.elastic())
                .subscribe(person::setName);
        
        System.out.println("outside of fromCallable");
        System.out.println("some operation 1");
        System.out.println("some operation 2");
        System.out.println("NAME (shouldn't be processed yet): " + person.getName());
        
        Thread.sleep(4000);
        System.out.println("some operation 3");
        System.out.println("NAME: " + person.getName());
        System.out.println("finish!");
    }
}

@Data
class Person {
    
    String name;
}
