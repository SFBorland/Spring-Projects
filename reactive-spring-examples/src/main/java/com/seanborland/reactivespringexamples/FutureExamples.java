package com.seanborland.reactivespringexamples;


import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FutureExamples {
    
    //Original example
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture.allOf(
//                IntStream.range(0, 2)
//                        .mapToObj(count -> CompletableFuture.supplyAsync(() ->
//                        {
//                            try {
//
//                                Thread.sleep(10000);
//                                System.out.println(String.format("[%s] Run %d", Thread.currentThread().getName(),
//                                        count));
//
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            return null;
//                        }))
//                        .collect(Collectors.toList())
//                        .toArray(new CompletableFuture[0]))
//                .get();
//        System.out.println("Done!");
//    }
    
    @Test
    public void completableFutureExample() throws InterruptedException {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Here from completableFuture");
        });
        
        Thread.sleep(9000);
        System.out.println("Outside test");
    }
    
    
    
    
    
    
    
    
    @Test
    public void completableFutureReturnExample() throws ExecutionException, InterruptedException {
        CompletableFuture<String> alpha = CompletableFuture.supplyAsync(() -> {
            
            System.out.println("alpha1 from completableFuture");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("alpha2 from completableFuture");
            return "alpha done";
        });
        
        CompletableFuture<String> bravo = CompletableFuture.supplyAsync(() -> {
            
            System.out.println("bravo1 from completableFuture");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("bravo2 from completableFuture");
            return "bravo done";
        });
        
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(alpha, bravo);
        
        combinedFuture.get();
        
        alpha.thenAccept(result -> System.out.println(result));
    }
    
    @Test
    public void bravo() throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            
            System.out.println("alpha1 from completableFuture");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("alpha2 from completableFuture");
            return "alpha done";
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            
            System.out.println("bravo1 from completableFuture");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("bravo2 from completableFuture");
            return "bravo done";
        }), (r1, r2) -> r1 + r2).thenAccept(System.out::println).join();
    }
    
    
    
    
    
    @Test
    public void monoExample() throws InterruptedException {
        Mono.fromRunnable(() -> {
            System.out.println("Inside mono");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println("Mono finished!");
        }).subscribeOn(Schedulers.elastic());
    
        System.out.println("Outside Mono, some operation 1");
        System.out.println("Outside Mono, some operation 2");
        Thread.sleep(9000);
        System.out.println("Outside test");
    }
    
    @Test
    public void monoReturnExample() throws InterruptedException {
        
        Mono<String> alpha = Mono.fromCallable(() -> {
            System.out.println("Inside mono");
            Thread.sleep(3000);
            return "result from mono";
        }).subscribeOn(Schedulers.elastic());
    
        Thread.sleep(4000);
        System.out.println("Outside test");
        System.out.println("Some operation 1");
        System.out.println("Some operation 2");
    
        alpha.subscribe();
        System.out.println();
        
        System.out.println("after blocking call");
        System.out.println("Before long sleep in main thread.");
        Thread.sleep(5000);
        System.out.println("After long sleep in main thread.");
    }
    
    @Test
    public void callableMono() throws InterruptedException {
        Mono.fromCallable(() -> {
            System.out.println("Inside Mono");
            Thread.sleep(5000);
            System.out.println("HERE!");
            return "hi";
        }).subscribeOn(Schedulers.elastic()).subscribe();
    
        Thread.sleep(10000);
        System.out.println("DONE!");
    }
    
    @Test
    public void callableMono2() throws InterruptedException {
        Mono.fromRunnable(() -> {
            System.out.println("Inside Mono");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("HERE!");
            
        }).subscribeOn(Schedulers.elastic()).subscribe();
        
        Thread.sleep(10000);
        System.out.println("DONE!");
    }
}
