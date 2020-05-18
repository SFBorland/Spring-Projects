package com.seanborland.reactivespringexamples.zipexamples;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class ZipWithExample {
    
    @Test
    public void alpha() {
        Mono.just(getOne())
                .zipWith(Mono.just(getTwo()))
                .subscribe(System.out::println);
    }
    
    @Test
    public void usingBlockInsteadOfSubscribe() {
        System.out.println(Thread.currentThread().getName());
        Mono.just(getOne())
                .zipWith(Mono.just(getTwo()))
                .doOnNext(System.out::println)
                .subscribeOn(Schedulers.elastic())
                .subscribe();
                //.block();
    }
    
    @Test
    public void usingFromCallable() {
        System.out.println(Thread.currentThread().getName());
        //Mono.fromCallable(() -> Mono.just(getOne())
        //        .zipWith(Mono.just(getTwo()))).subscribeOn(Schedulers.elastic())
        //        .subscribe();
    
        Mono.zip(getOne(), getTwo())
                .doOnNext(System.out::println)
                .subscribeOn(Schedulers.elastic())
                .subscribe();
        //.block();
    }
    
    @Test
    public void usingFromCallableUsingZip() {
        System.out.println("Called in originalMethod: " + Thread.currentThread().getName());
        Mono.fromCallable(() -> Mono.zip(getOne(), getTwo()))
                .subscribeOn(Schedulers.elastic())
                .block();
    }
    
    @Test
    public void usingZipSoBothMonosExecuteAsync() {
        
        Mono.zip(Mono.just(getOne()), Mono.just(getTwo()))
                .subscribe();
    }
    
    @Test
    public void usingZipWithDelayToTestBecauseSleepDoesntWork() {
        System.out.println("Called in originalMethod: " + Thread.currentThread().getName());
        Mono.zip(getWithDelayOne(3), getWithDelayTwo(3))
                .doOnNext(System.out::println)
                .block();
    }
    
    private Mono<String> getOne() {
        System.out.println("Called in getOne: " + Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
            System.out.println("one done!");
        } catch (InterruptedException e) {
        
        }
        return Mono.just("one");
    }
    
    private Mono<String> getTwo() {
        System.out.println("Called in getTwo: " + Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
            System.out.println("two done!");
        } catch (InterruptedException e) {
        
        }
        return Mono.just("two");
    }
    
    private Mono<String> getWithDelayOne(int delayTime) {
        System.out.println("Called in getWithDelayOne: " + Thread.currentThread().getName());
        return Mono.just("one").delayElement(Duration.ofSeconds(delayTime));
    }
    
    private Mono<String> getWithDelayTwo(long delayTime) {
        System.out.println("Called in getWithDelayTwo: " + Thread.currentThread().getName());
        return Mono.just("two").delayElement(Duration.ofSeconds(delayTime));
    }
}

/*
return Mono.fromCallable(() -> {
  Thread.sleep(2000);
  //...
})
.subscribeOn(Schedulers.elastic())
.publishOn(REACTOR_HTTP_NIO_SCHEDULER)
.then(Mono.just("Delayed Greetings"));
 */
