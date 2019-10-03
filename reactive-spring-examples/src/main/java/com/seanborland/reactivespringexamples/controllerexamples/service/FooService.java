package com.seanborland.reactivespringexamples.controllerexamples.service;

import reactor.core.publisher.Mono;

public interface FooService {
    
    Mono<String> getBar();
}
