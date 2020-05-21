package com.seanborland.reactivespringexamples.monoexamples;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Function;

@Slf4j
public class MonoExamples {
    
    /**
     * doOnSuccess is used on Mono's. It can be applied on mulitple methods in the pipelin and will only execute when
     * the it recieves an onComplete signal and still maintains the last value (unlike Flux which does not maintain
     * value)
     */
    @Test
    public void doOnSuccessIsForMono() {
        Mono.just(1)
                .map(Function.identity())
                .doOnNext(result -> System.out.println("doOnNext prints: " + result))
                .map(integer -> integer + 1)
                .doOnSuccess(System.out::println)
                .map(integer -> integer + 1)
                .block();
    }
    
    @Test
    public void doOnSubscribeExampleSpecificallyToLogSomeProcessThatStarted() {
        Mono.just(1000)
                .doOnSubscribe(result -> log.debug(">>> Started <<<"))
                .map(Function.identity())
                .doOnNext(result -> log.debug(result.toString()))
                .map(integer -> integer + 1)
                .doOnNext(result -> log.debug(result.toString()))
                .doOnSuccess(result -> log.debug(">>> Complete <<<"))
                .block();
    }
    
    @Test
    public void usingTimerInDoOnSuccess() {
        Instant start = Instant.now();
        Mono.just(1000)
                .doOnSubscribe(result -> log.debug(">>> Started <<<"))
                .map(Function.identity())
                .doOnNext(result -> log.debug(result.toString()))
                .delayElement(Duration.ofSeconds(2))
                .map(integer -> integer + 1)
                .doOnNext(result -> log.debug(result.toString()))
                .doOnSuccess(result -> log.debug(">>> Complete <<<"))
                .doOnSuccess(result -> log.debug("Total time taken: " + Duration.between(start, Instant.now()).getSeconds() + "s"))
                .subscribeOn(Schedulers.elastic())
                .block();
    }
    
    @Test
    public void runnableMono() {
        System.out.println("runnableMono started...");
        
        //**fromRunnable creates a Mono that completes empty once the provided Runnable has been executed**/
        Mono.fromRunnable(() -> {
            System.out.println("Runnable Started!");
            sleep(3000);
            System.out.println("Runnable has returned!+");
        })
                .subscribeOn(Schedulers.elastic())
                .subscribe();
        
        System.out.println("Outside of mono");
        System.out.println("Some operation 1");
        sleep(1500);
        System.out.println("Some operation 2");
        sleep(1500);
        System.out.println("End of main.");
    }
    
    @Test
    public void callableMonoReturnResult() {
        
        System.out.println("callableMono started...");
        
        Mono<String> monoResult = Mono.fromCallable(() -> {
            System.out.println("Inside callable mono.");
            
            sleep(3000);
            
            System.out.println("Inside callable, sleep done!");
            
            return "I'm the return value of the fromCallable";
        }).subscribeOn(Schedulers.elastic());
        
        System.out.println("outside of fromCallable");
        
        monoResult.map(result -> {
            System.out.println("Result before map: " + result);
            return "I overwrote the result!!";
        }).subscribe(System.out::println);
        
        System.out.println("after monoResult.map(...)");
        
        sleep(4000);
        
        System.out.println("after thread.sleep");
    }
    
    @Test
    public void callableWithParamPassed() {
        System.out.println("Starting..");
        
        Mono.fromCallable(this::getMyName)
                .map(name -> name = name + " Borland.")
                .subscribeOn(Schedulers.elastic())
                .subscribe(this::useValueReturnedFromMono);
        
        System.out.println("After mono call");
        sleep(12000);
        System.out.println("After main thread sleep.");
    }
    
    private String getMyName() {
        System.out.println("Inside getMyName method!");
        sleep(3000);
        return "Sean";
    }
    
    private void useValueReturnedFromMono(String valueToUse) {
        System.out.println("External method likes " + valueToUse);
    }
    
    @Test
    public void callableUpdatingPojo() {
        
        System.out.println("callableMono started...");
        
        Person person = new Person();
        System.out.println(Thread.currentThread().getName());
        Mono.fromCallable(() -> {
            System.out.println("Inside callable mono.");
            System.out.println(Thread.currentThread().getName());
            sleep(3000);
            
            System.out.println("Inside callable, sleep done!");
            
            return "SEAN";
        })
                .subscribeOn(Schedulers.elastic()).block();
        //.subscribe(person::setName);
        
        System.out.println("outside of fromCallable");
        System.out.println("some operation 1");
        System.out.println("some operation 2");
        System.out.println("NAME (shouldn't be processed yet): " + person.getName());
        
        sleep(4000);
        System.out.println("some operation 3");
        System.out.println("NAME: " + person.getName());
        System.out.println("finish!");
    }
    
    @Data
    class Person {
        
        String name;
    }
    
    @Test
    public void twoAsyncCalls() throws InterruptedException {
        
        //Scheduler s = Schedulers.newParallel("parallel-scheduler", 2);
        Scheduler s = Schedulers.elastic();
        
        System.out.println(Thread.currentThread().getName());
        
        Mono<Tuple2<String, String>> hanu = Mono.fromCallable(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Inside callable 1.");
            
            Thread.sleep(3000);
            
            System.out.println("Callable 1, sleep done!");
            
            return "SEAN";
        }).subscribeOn(s)
                .zipWith(Mono.fromCallable(() -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("Inside callable 2.");
                    
                    Thread.sleep(3000);
                    
                    System.out.println("Callable 2, sleep done!");
                    
                    return "Borland";
                })).subscribeOn(s);
        
        System.out.println(hanu.map(result -> result.getT1() + " and " + result.getT2()).subscribe());
        
        System.out.println("Outside calls, before sleep.");
        Thread.sleep(5000);
        System.out.println("Outside calls, after sleep.");
    }
    
    private void sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
