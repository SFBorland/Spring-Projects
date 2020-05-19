package com.seanborland.reactivespringexamples.zipexamples;

import lombok.SneakyThrows;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.Instant;

public class ZipWithExample {
    
    @Test//zipwithsleep
    public void usingZipWithExecsFirstThenSecondMono() {
        System.out.println(Thread.currentThread().getName());
        sleepOne()
                .zipWith(sleepTwo())
                .doOnNext(System.out::println)
                .subscribeOn(Schedulers.elastic())
                .subscribe();
    }
    
    @Test//delayandzipwith
    public void delayAndZipWith() {
        System.out.println(Thread.currentThread().getName());
        delayOne(3)
                .zipWith(delayTwo(3))
                .doOnNext(System.out::println)
                .subscribeOn(Schedulers.elastic())
                .block();
    }
    
    @Test
    public void alpha() {
        Instant start = Instant.now();
        System.out.println("Calling method: " + Thread.currentThread().getName());
        Mono.zip(delayOne(3), delayTwo(3))
                .doOnNext(System.out::println)
                .block();
        
        Instant finish = Instant.now();
        System.out.println(Duration.between(start, finish).getSeconds() + "s");
    }
    
    @Test
    public void bravo() {
        Instant start = Instant.now();
        System.out.println("Calling method: " + Thread.currentThread().getName());
        delayOne(3)
                .zipWith(delayTwo(3))
                .doOnNext(System.out::println)
                .block();
        
        Instant finish = Instant.now();
        System.out.println(Duration.between(start, finish).getSeconds() + "s");
    }
    
    @Test
    public void usingSleepZipAndSubscribe() {
        System.out.println(Thread.currentThread().getName());
        
        Mono.zip(sleepOne().subscribeOn(Schedulers.elastic()), sleepTwo().subscribeOn(Schedulers.elastic()))
                //.doOnNext(System.out::println)
                .subscribeOn(Schedulers.elastic())
                .subscribe(System.out::println);
    }
    
    @Test
    public void usingZipSleepAndFromCallable() {
        System.out.println("Called in originalMethod: " + Thread.currentThread().getName());
        Mono.fromCallable(() -> Mono.zip(sleepOne(), sleepTwo()))
                //.subscribeOn(Schedulers.elastic())
                .block();
    }
    
    @Test
    public void usingZipWithDelayToTestBecauseSleepDoesntWork() {
        System.out.println("Called in originalMethod: " + Thread.currentThread().getName());
        Mono.zip(delayOne(3), delayTwo(3))
                .doOnSuccess(System.out::println)
                .block();
    }
    
    
    private Mono<String> sleepOne() {
        System.out.println("Called in sleepOne: " + Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
            System.out.println("sleepOne done!");
        } catch (InterruptedException e) {
        
        }
        return Mono.just("sleepOne");
    }
    
    private Mono<String> sleepTwo() {
        System.out.println("Called in sleepTwo: " + Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
            System.out.println("sleepTwo done!");
        } catch (InterruptedException e) {
        
        }
        return Mono.just("sleepTwo");
    }
    
    private Mono<String> delayOne(int delayTime) {
        System.out.println("Called in delayOne: " + Thread.currentThread().getName());
        return Mono.just("delayOne")
                .delayElement(Duration.ofSeconds(delayTime));
    }
    
    private Mono<String> delayTwo(long delayTime) {
        System.out.println("Called in delayTwo: " + Thread.currentThread().getName());
        return Mono.just("delayTwo")
                .delayElement(Duration.ofSeconds(delayTime));
    }
}
