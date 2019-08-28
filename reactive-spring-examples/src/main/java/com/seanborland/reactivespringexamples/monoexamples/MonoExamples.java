package com.seanborland.reactivespringexamples.monoexamples;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

@Slf4j
public class MonoExamples {

    @Test
    public void runnableMono() throws InterruptedException {
        System.out.println("runnableMono started...");

        //**fromRunnable creates a Mono that completes empty once the provided Runnable has been executed**/
        Mono.fromRunnable(() -> {
            System.out.println("Runnable Started!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runnable has returned!+");
        }).subscribeOn(Schedulers.elastic()).subscribe();

        System.out.println("Outside of mono");

        System.out.println("Some operation 1");
        Thread.sleep(1500);

        System.out.println("Some operation 2");
        Thread.sleep(1500);

        System.out.println("End of main.");
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
    public void callableUpdatingPojo() throws InterruptedException {

        System.out.println("callableMono started...");

        Person person = new Person();
        System.out.println(Thread.currentThread().getName());
        Mono.fromCallable(() -> {
            System.out.println("Inside callable mono.");
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(3000);

            System.out.println("Inside callable, sleep done!");

            return "SEAN";
        })
                .subscribeOn(Schedulers.elastic()).block();
                //.subscribe(person::setName);

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
}
