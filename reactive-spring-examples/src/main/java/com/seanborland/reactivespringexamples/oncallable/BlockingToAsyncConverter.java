package com.seanborland.reactivespringexamples.oncallable;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.function.Function;

@Slf4j
public class BlockingToAsyncConverter {
    
    /**
     * This test will execute the two sleep methods sequentially even though we're using subscribeOn(). The reason is
     * because we're calling subscribeOn() on the Mono.fromCallable and not the individual calls to sleep(). So the
     * Mono.zip will execute in a separate thread freeing up the Main thread but this new elasitc thread will call both
     * sleep methods thus block on each call.
     */
    @Test
    @SneakyThrows
    public void usingZipSleepAndFromCallable() {
        System.out.println("Calling method from thread: " + Thread.currentThread().getName());
        Mono.fromCallable(() -> Mono.zip(Mono.just(blockingCall(4000)), Mono.just(blockingCall(4000))))
                .subscribeOn(Schedulers.elastic())
                .subscribe();
        System.out.println("*** After blockingCall() ***");
        Thread.sleep(10000);
    }
    
    @Test
    public void callableZipOnTwoSleepMethodsExplicitlyWrittenOut() {
        Instant start = Instant.now();
        
        System.out.println("Calling method on thread: " + Thread.currentThread().getName());
        Mono<String> mono1 = Mono.fromCallable(() -> blockingCall(3000))
                .subscribeOn(Schedulers.elastic());
        
        Mono<String> mono2 =
                Mono.fromCallable(() -> blockingCall(3000))
                        .subscribeOn(Schedulers.elastic());
        
        mono1.zipWith(mono2)
                .doOnSuccess(System.out::println)
                .block();
        
        Instant finish = Instant.now();
        System.out.println(Duration.between(start, finish).getSeconds() + "s");
    }
    
    @Test
    public void callableZipOnTwoSleepMethodsFunctionallyWrittenOut() {
        Instant start = Instant.now();
        
        System.out.println("Calling method: " + Thread.currentThread().getName());
        Mono.fromCallable(() -> blockingCall(3000))
                .subscribeOn(Schedulers.elastic())//sleep returns a mono so would be Mono<Mono<String>>
                .zipWith(Mono.fromCallable(() -> blockingCall(3000))
                        .subscribeOn(Schedulers.elastic()))
                .doOnSuccess(System.out::println)
                .block();
        
        Instant finish = Instant.now();
        System.out.println(Duration.between(start, finish).getSeconds() + "s");
    }
    
    @Test
    @SneakyThrows
    public void makeBlockingCallAsyncDONE() {
        Instant start = Instant.now();
        
        System.out.println("Calling method from thread: " + Thread.currentThread().getName());
        Mono.fromCallable(() -> blockingCall(3000))
                .subscribeOn(Schedulers.elastic())
                .subscribe(System.out::println);
        System.out.println("*** After blockingCall() ***");
        
        Thread.sleep(5000);//this is to wait for the result since it's now running on a different thread.
        
        Instant finish = Instant.now();
        System.out.println("Total time: " + Duration.between(start, finish).getSeconds() + "s");
    }
    
    @SneakyThrows
    private String blockingCall(int blockTime) {
        System.out.println("blockingMethod from thread: " + Thread.currentThread().getName());
        Thread.sleep(blockTime);
        System.out.println("blockingCall done!");
        return "blockingResult";
    }
    
    ///
    @Test
    @SneakyThrows
    public void xxx() {
        
        //Mono<String> result = Mono.fromCallable(() -> blockingCall(3000))
        //        .subscribeOn(Schedulers.elastic());
        //
        //StepVerifier.create(result)
        //        .expectSubscription()
        //.expectNext("blockingResults")
        //.expectComplete();
        //        //.subscribe(System.out::println);
        
        Flux.fromIterable(Arrays.asList("1", "2"))
                //.subscribeOn(Schedulers.elastic())
                .log()
                //.subscribe(System.out::println);
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        //s.request(Long.MAX_VALUE);
                        s.request(1);
                        System.out.println("Sean - onSubscribe");
                    }
                    
                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext called");
                    }
                    
                    @Override
                    public void onError(Throwable t) {
                    
                    }
                    
                    @Override
                    public void onComplete() {
                        System.out.println("sean - onComplete called.");
                    }
                });
        
        Thread.sleep(3000);
    }
    
    @Test
    public void doOnCompleteExampleIsForFlux() {
        Flux.fromIterable(Arrays.asList(1, 2, 3))
                .map(Function.identity())
                .doOnNext(result -> log.debug(result.toString()))
                .map(integer -> integer * 2)
                .doOnNext(result -> log.debug(result.toString()))
                .doOnSubscribe(result -> log.info(">>> Started <<<"))
                .doOnComplete(() -> log.debug(">>> Complete <<<"))
                .blockLast();
    }
    
    @Test
    public void doOnSuccessIsForMono() {
        Mono.just(1)
                .map(Function.identity())
                .doOnNext(result -> {
                    System.out.println("doOnNext prints: " + result);
                })
                .map(integer -> integer + 1)
                .doOnSuccess(result -> System.out.println("doOnSuccess prints: " + result))
                .map(integer -> integer + 1)
                .block();
    }
    
    @Test
    public void showingUseCaseForLogFunction() {
        Flux.fromIterable(Arrays.asList(1, 2, 3))
                .map(Function.identity())
                .log("First log()")//This won't log anything that happens after this.
                .map(integer -> integer * 2)
                .log("Second log()")//Logs everything up to this stage in the pipeline.
                .blockLast();
    }
}
