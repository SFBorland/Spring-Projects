package com.seanborland.reactivespringexamples.controllerexamples.repository;

import reactor.core.publisher.Mono;

public interface FooRepository {
    Mono<String> getBar();
}
