package com.seanborland.reactivespringexamples;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TempRandomsToOrganize {
    
    /**
     * Example showing the range() function which emits Integers starting at the "start" value, incrementing by 1 until
     * the "count" value has been reached.
     */
    @Test
    public void simpleRangeExample() {
        Flux.range(1, 4)
                .log()
                .blockLast();
    }
    
    /**
     * doOnNext - In this example we can truly see the use of doOnNext AND difference between .map(). So, doOnNext will
     * NOT mutate the emitted value meaning you can increment the emitted value, then put that value in a separate list
     * or print etc and the next stage in the pipeline will receive the original emitted value and not the one you
     * incremented. So all changes are isolated to that stages scope. .map() on the other hand can mutate and even
     * return an entirely different value all together.
     */
    @Test
    public void doOnNextDetailedExample() {
        List<String> names = new ArrayList<>(Arrays.asList("Sean", "Desiree", "Brandon", "Elle"));
        List<String> fullName = new ArrayList<>();
        
        Flux.fromIterable(names)
                .doOnNext(name -> fullName.add(name + " Borland"))
                .doOnNext(name -> System.out.println("Original emitted value was NOT mutated: " + name))
                .doOnComplete(() -> System.out.println("Values in list where I mutated: " + fullName))
                .blockLast();
    }
    
    /**
     * Delay the elements of the specified flux(set size) by a set time interval. This is different from interval()
     * where there is no set number of values in a flux just an infinite stream of items emitted for the specified time
     * interval.
     */
    @Test
    public void simpleDelayElementExample() {
        Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(System.out::println)
                .blockLast();
    }
    
    /**
     * Delay element of the Flux (infinite size) by a set time interval. This differs from delayElements() in that the
     * Flux does not have a set size and it Emits Longs.
     */
    @Test
    public void simpleTimeIntervalExample() {
        Flux.interval(Duration.ofSeconds(1))
                .doOnNext(System.out::println)
                .blockLast();
    }
    
    /**
     * TODO: limitRequest description.
     */
    @Test
    public void exampleOfLimitRequest() {
        Flux.interval(Duration.ofSeconds(1))
                .log("Initial value")
                .limitRequest(6)
                .collectList()
                .log("Final result") //This wont log the onCancel
                .block();
    }
    
    /**
     * This example shows one of the Thread pool abstractions "Schedulers", here you'll see that I/O (network bound)
     * operations are apart of the [reactor-http-nio-<#>] thread group. NOTE: CPU bound operations use [parallel-<#>]
     */
    @Test
    @SneakyThrows
    public void showWebclientUsesElasticUsesReactorHttpNioInLog() {
        WebClient.create("http://www.google.com")
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .log(">>> MyExample <<<")
                .subscribe();
        System.out.println("*********");
        Thread.sleep(5000);
    }
    
    /**
     * This example shows one of the Thread pool abstractions "Schedulers", here you'll see that CPU bound operations
     * use [parallel-<#>]. NOTE: I/O (network bound)operations are apart of the [reactor-http-nio-<#>] thread group.
     */
    @Test
    public void showCpuBoundUsesParallelInLog() {
        Flux.interval(Duration.ofSeconds(1))
                .take(5)
                .log()
                .blockLast();
    }
    
    @Test
    @SneakyThrows
    public void schedulersThreadPoolAbstractionExample() {
        Flux.interval(Duration.ofSeconds(1), Schedulers.newElastic("myElastic"))
                .take(5)
                .log("First")
                .subscribe();
                
    
        Flux.interval(Duration.ofSeconds(1), Schedulers.newParallel("myParallel"))
                .take(5)
                .log("Second")
                .subscribe();
    
        Flux.interval(Duration.ofSeconds(1), Schedulers.newSingle("mySingle"))
                .take(5)
                .log("Third")
                .subscribe();
        
        Thread.sleep(6000);
    }
}
