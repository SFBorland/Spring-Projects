package com.seanborland.reactivespringexamples.controllerexamples.service;

import com.seanborland.reactivespringexamples.controllerexamples.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FooServiceImpl implements FooService {
    
    private FooRepository fooRepository;
    
    @Autowired
    public FooServiceImpl(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }
    
    @Override
    public Mono<String> getBar() {
        return fooRepository.getBar();
    }
}
