package com.seanborland.reactivespringexamples.doonexamples;

import org.junit.Test;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class DoOnMonoExamples {
    
    @Test
    public void doOnSuccessIsForMono() {
        Mono.just(1)
                .map(Function.identity())
                .doOnNext(result -> System.out.println("doOnNext prints: " + result))
                .map(integer -> integer + 1)
                .doOnSuccess(result -> System.out.println("doOnSuccess prints: " + result))
                .map(integer -> integer + 1)
                .map(integer -> integer + 1)
                .map(integer -> integer + 1)
                .map(integer -> integer + 1)
                .map(integer -> integer + 1)
                .log()
                .block();
    }
}
