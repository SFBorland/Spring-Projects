package com.seanborland.reactivespringexamples;

import org.junit.Test;
import reactor.core.publisher.Flux;
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
    public void callableMono() throws InterruptedException {
    
        System.out.println("callableMono started...");
        
        Mono<String> monoResult = Mono.fromCallable(() -> {
            System.out.println("Inside callable mono.");
            
            Thread.sleep(3000);
    
            System.out.println("Inside callable, sleep done!");
            
            return "I'm the return value of the fromCallable";
        }).subscribeOn(Schedulers.elastic());
        monoResult.subscribe(System.out::println);
        
        System.out.println("outside of fromCallable");
        
        Thread.sleep(4000);
        
        System.out.println("after thread.sleep");
    }
    
    @Test
    public void callableMono2() throws InterruptedException {
        
        System.out.println("callableMono started...");
        
        Mono<String> monoResult = Mono.fromCallable(() -> {
            System.out.println("Inside callable mono.");
            
            Thread.sleep(3000);
            
            System.out.println("Inside callable, sleep done!");
            
            return "I'm the return value of the fromCallable";
        }).subscribeOn(Schedulers.elastic());
        
//        monoResult.map(result -> {
//            System.out.println("RESULT: " + result);
//            return 1;
//        }).subscribe();
        
        
        System.out.println("outside of fromCallable");
        
        Thread.sleep(4000);
        //String value = monoResult.block();
        
        System.out.println("after thread.sleep");
    }
    
//    @Test
//    public void monoRunningInMainThread() throws InterruptedException {
//
//        Mono<String> mono = Mono.just("hello ");
//
//        new Thread(() -> mono
//                .map(msg -> msg + "thread ")
//                .subscribe(v ->
//                        System.out.println(v + Thread.currentThread().getName())
//                )
//        ).join();
//    }
    
//    public static void main(String[] args) throws InterruptedException {
//        final Mono<String> mono = Mono.just("hello ");
//
//        new Thread(() -> mono
//                .map(msg -> msg + "thread ")
//                .subscribe(v ->
//                        System.out.println(v + Thread.currentThread().getName())
//                )
//        ).join();
//
//    }
}
