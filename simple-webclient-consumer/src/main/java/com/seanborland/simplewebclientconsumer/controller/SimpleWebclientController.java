package com.seanborland.simplewebclientconsumer.controller;

import com.seanborland.simplewebclientconsumer.service.SimpleWebclientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class SimpleWebclientController {
    
    private SimpleWebclientService simpleWebclientService;
    
    @Autowired
    public SimpleWebclientController(SimpleWebclientService simpleWebclientService) {
        this.simpleWebclientService = simpleWebclientService;
    }
    
    @GetMapping("/reactive-consumer")
    public Flux<String> callSlowProducerTwo() {
        
        System.out.println("Controller Thread: " + Thread.currentThread().getName());
        
        System.out.println("Calling Very Slow Producer");
        Mono<String> resultMono2 = simpleWebclientService.callVerySlowProducer();//.subscribeOn(Schedulers.elastic());
        System.out.println("after Very Slow Producer call");
        
        System.out.println("Calling Slow Producer");
        Mono<String> resultMono = simpleWebclientService.callSlowProducer();//.subscribeOn(Schedulers.elastic());
        System.out.println("after Slow Producer call");
        
        return resultMono.mergeWith(resultMono2);
    }
}
/*
RESULTS:


Controller Thread: reactor-http-nio-5
Calling Very Slow Producer
Inside very slow producer service call: reactor-http-nio-5
after Very Slow Producer call
Calling Slow Producer
Inside slow producer service call: reactor-http-nio-5
after Slow Producer call
2019-10-12 12:17:47.536  INFO 87094 --- [ctor-http-nio-5] reactor.Mono.FlatMap.2                   : | onSubscribe([Fuseable] MonoFlatMap.FlatMapMain)
2019-10-12 12:17:47.538  INFO 87094 --- [ctor-http-nio-5] reactor.Mono.FlatMap.2                   : | request(32)
2019-10-12 12:17:47.549  INFO 87094 --- [ctor-http-nio-5] reactor.Mono.FlatMap.1                   : | onSubscribe([Fuseable] MonoFlatMap.FlatMapMain)
2019-10-12 12:17:47.549  INFO 87094 --- [ctor-http-nio-5] reactor.Mono.FlatMap.1                   : | request(32)
2019-10-12 12:17:57.614  INFO 87094 --- [ctor-http-nio-2] reactor.Mono.FlatMap.2                   : | onNext(Hi, from Producer!)
2019-10-12 12:17:57.614  INFO 87094 --- [ctor-http-nio-4] reactor.Mono.FlatMap.1                   : | onNext(Hi, from a very slow Producer!)
2019-10-12 12:17:57.617  INFO 87094 --- [ctor-http-nio-4] reactor.Mono.FlatMap.1                   : | onComplete()
2019-10-12 12:17:57.622  INFO 87094 --- [ctor-http-nio-2] reactor.Mono.FlatMap.2                   : | onComplete()

NOTES:
    1. I didn't do subscribeOn(Schdulers.elastic) on each service call so notice same onSubscribe() thread '[ctor-http-nio-5]'
    2. Is "[ctor-http-nio-5]" an event-loop thread or worker? thread?
    3. Each call was actually 10s but notice the time took exacly 10s and not 20.
 */

