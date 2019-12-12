package com.seanborland.reactivespringexamples.asyncexamples;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONUtil;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class MonoAsyncExample {
    
    @Test
    public void standardSequentialExample() {
        
        log.info("Some method call 1");
        
        log.info(blockingCall(2000));
        
        log.info("Some method call 2");
    }
    
    @Test()
    public void makeCallAsyncButLazyAndNoSubscribe() {
        
        //** The blocking call returns a Mono and is wrapped in a callable, but the lazy nature of a Mono means the
        // blocking call will not get executed until you call .subscribe.
        
        log.info("Some method call 1 from " + Thread.currentThread().getName());
        
        Mono<String> blockingCallInMono = Mono.fromCallable(() -> blockingCall(4000));
        
        log.info("Some method call 2 from " + Thread.currentThread().getName());
        
        log.info("Some method call 3 from " + Thread.currentThread().getName());
    }
    
    @Test
    public void charlie() {
        
        //** The blocking call is lazy and will be executed AFTER call 2 because that's when .subcribe() is called at
        // which point it block.
        
        log.info("Some method call 1 from " + Thread.currentThread().getName());
        
        Mono<String> blockingCallInMono = Mono.fromCallable(() -> blockingCall(4000));
        
        log.info("Some method call 2 from " + Thread.currentThread().getName());
        
        blockingCallInMono.subscribe(System.out::println);
        
        log.info("Some method call 3 from " + Thread.currentThread().getName());
    }
    
    @Test
    public void delta() {
        
        //** Here we offload the blocking call to a separate thread using .subscribeOn(Schedulers.elastic()), the result
        // is the program completes but doesn't wait for the result.
        
        log.info("Some method call 1 from " + Thread.currentThread().getName());
        
        Mono<String> blockingCallInMono = Mono.fromCallable(() -> blockingCall(4000))
                .subscribeOn(Schedulers.elastic())
                .map(result -> result + " adding this in a .map()");
    
        Mono<String> blockingCallInMono2 = Mono.fromCallable(() -> blockingCall(4000))
                .subscribeOn(Schedulers.elastic())
                .map(result -> result + " adding this in a .map()");
        
        log.info("Some method call 2 from " + Thread.currentThread().getName());
        
        blockingCallInMono.mergeWith(blockingCallInMono2).subscribe(System.out::println);
//        blockingCallInMono.subscribe(System.out::println);
//        blockingCallInMono2.subscribe(System.out::println);
        
        
        log.info("Some method call 3 from " + Thread.currentThread().getName());
        
    }
    
    private String blockingCall(int delay) {
        
        log.info("blockingCall start from " + Thread.currentThread().getName());
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "blockingCall complete";
    }
}
