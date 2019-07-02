//package com.seanborland.reactivespringexamples.futureexamples;
//
//
//import org.junit.Test;
//
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//
//public class FutureExamples {
//
//    //Original example with Gabriel
////    public static void main(String[] args) throws ExecutionException, InterruptedException {
////        CompletableFuture.allOf(
////                IntStream.range(0, 2)
////                        .mapToObj(count -> CompletableFuture.supplyAsync(() ->
////                        {
////                            try {
////
////                                Thread.sleep(10000);
////                                System.out.println(String.format("[%s] Run %d", Thread.currentThread().getName(),
////                                        count));
////
////                            } catch (InterruptedException e) {
////                                e.printStackTrace();
////                            }
////                            return null;
////                        }))
////                        .collect(Collectors.toList())
////                        .toArray(new CompletableFuture[0]))
////                .get();
////        System.out.println("Done!");
////    }
//
//    @Test
//    public void simpleRunAsyncExample() throws InterruptedException {
//        CompletableFuture.runAsync(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Here from completableFuture");
//        });
//
//        Thread.sleep(5000);
//        System.out.println("Outside test");
//    }
//
//
//    @Test
//    public void twoSupplyAsyncAndCombineUsingAllOf() throws ExecutionException, InterruptedException {
//        CompletableFuture<String> alpha = CompletableFuture.supplyAsync(() -> {
//
//            System.out.println("alpha1 from completableFuture");
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("alpha2 from completableFuture");
//            return "alpha done";
//        });
//
//        CompletableFuture<String> bravo = CompletableFuture.supplyAsync(() -> {
//
//            System.out.println("bravo1 from completableFuture");
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("bravo2 from completableFuture");
//            return "bravo done";
//        });
//
//        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(alpha, bravo);
//
//        combinedFuture.get();
//
//        alpha.thenAccept(result -> System.out.println(result));
//    }
//
//    @Test
//    public void sendTwoAsyncRequestsAndWaitForBoth() {
//        CompletableFuture.supplyAsync(() -> {
//
//            System.out.println("alpha1 from completableFuture");
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("alpha2 from completableFuture");
//            return "alpha done";
//        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
//
//            System.out.println("bravo1 from completableFuture");
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("bravo2 from completableFuture");
//            return "bravo done";
//        }), (r1, r2) -> r1 + r2).thenAccept(System.out::println).join();
//    }
//}
