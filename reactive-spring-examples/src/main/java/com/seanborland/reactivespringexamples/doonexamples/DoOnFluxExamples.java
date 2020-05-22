package com.seanborland.reactivespringexamples.doonexamples;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Slf4j
public class DoOnFluxExamples {
    
    @Test
    public void showingUseCaseForLogFunction() {
        
        Flux.range(1, 5)
                .map(Function.identity())
                .doOnNext(result -> System.out.println("First doOnNext - result: " + result))
                .doOnComplete(() -> System.out.println("First doOnComplete called."))
                .map(integer -> integer * 2)
                .doOnNext(result -> System.out.println("Second doOnNext - result: " + result))
                .doOnComplete(() -> System.out.println("Second doOnComplete called."))
                .blockLast();
    }
    
    
}
