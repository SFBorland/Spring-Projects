package com.seanborland.reactivespringexamples.error.handling.examples;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

@Slf4j
public class ErrorsInSubscribe {
    
    @Test
    public void handleErrorInSubscribe() {
        Flux.range(1, 5)
                .map(integer -> {
                    if (integer == 3) {
                     throw new IndexOutOfBoundsException();
                    }
                    return integer;
                })
                .subscribe(integer -> System.out.println("Value = " + integer), this::handleError);
    }
    
    @Test
    public void simpleDoOnErrorExample() {
        Flux.range(1, 5)
                .doOnError(this::handleError)//This will never catch the error since it happens after this.
                .map(integer -> {
                    if (integer == 3) {
                        throw new IndexOutOfBoundsException();
                    }
                    return integer;
                })
                .doOnNext(System.out::println)
                .doOnError(this::handleError)//This will catch any exception up to this point.
                .map(integer -> ++integer)
                .subscribe();
                
    }
    
    private void handleError(Throwable throwable) {
        System.out.println(">> Exception thrown: " + throwable.getClass() + " <<<");
    }
}
