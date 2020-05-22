package com.seanborland.reactivespringexamples;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class TempRandomsToOrganize {
    
    @Test
    public void showingLogExample() {
        Flux.range(1, 4)
                .log()
                .blockLast();
    }
}
