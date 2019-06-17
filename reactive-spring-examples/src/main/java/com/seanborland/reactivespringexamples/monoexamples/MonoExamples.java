package com.seanborland.reactivespringexamples.monoexamples;

import lombok.Data;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoExamples {
    
    @Test
    public void runnableMono() throws InterruptedException {
        System.out.println("runnableMono started...");
        
        //**fromRunnable creates a Mono that completes empty once the provided Runnable has been executed**/
        Mono.fromRunnable(() -> {
            System.out.println("Inside runnable");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("runnable sleep done!");
        }).subscribeOn(Schedulers.elastic()).subscribe();
        System.out.println("Outside of mono");
        
        System.out.println("Some operation 1");
        System.out.println("Some operation 2");
        Thread.sleep(5000);
    
        System.out.println("After sleep.");
    }
    
    @Test
    public void callableMonoReturnResult() throws InterruptedException {
    
        System.out.println("callableMono started...");
        
        Mono<String> monoResult = Mono.fromCallable(() -> {
            System.out.println("Inside callable mono.");
            
            Thread.sleep(3000);
    
            System.out.println("Inside callable, sleep done!");
            
            return "I'm the return value of the fromCallable";
        }).subscribeOn(Schedulers.elastic());
        
        monoResult
                .map(result -> {
                    System.out.println("Result before map: " + result);
                    return "I overwrote the result!!";
                })
                .subscribe(System.out::println);
        
        System.out.println("outside of fromCallable");
        
        Thread.sleep(4000);
        
        System.out.println("after thread.sleep");
    }
    
    @Test
    public void callableWithParamPassed() throws InterruptedException {
        System.out.println("Starting..");
        
        Mono.fromCallable(this::getMyName)
                .map(name -> name = name + " Borland.")
                .subscribeOn(Schedulers.elastic())
                .subscribe(this::useValueReturnedFromMono);
    
        System.out.println("After mono call");
        Thread.sleep(12000);
        System.out.println("After main thread sleep.");
    }
    
    private String getMyName() throws InterruptedException {
        System.out.println("Inside getMyName method!");
        Thread.sleep(3000);
        return "Sean";
    }
    
    private void useValueReturnedFromMono(String valueToUse) {
        System.out.println("External method likes " + valueToUse);
    }
    
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
    
    @Data
    class Person {
        
        String name;
    }
}
