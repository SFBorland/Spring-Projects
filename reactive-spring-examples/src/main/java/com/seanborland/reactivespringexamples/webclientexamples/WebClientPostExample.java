package com.seanborland.reactivespringexamples.webclientexamples;

import net.minidev.json.JSONUtil;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WebClientPostExample {
    
    @Test
    public void postCallToEpo() {
        
        WebClient.create("http://shiptst1.mesos.rccl.com")
                .post()
                .uri("/offering/v1/en/royal/mobile/offering")
                .body(Mono.just(new OfferingRequest()), OfferingRequest.class)
                .retrieve()
                .bodyToMono(String.class)
                .log()
                .block(Duration.ofSeconds(20));
    }
    
    //@Test
    public String callSlowGithub() {
        
        return WebClient.create("http://slowwly.robertomurray.co.uk/delay/8000/url/http://www.github.com")
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .log()
                .block(Duration.ofSeconds(20));
    }
    
    @Test
    public void alpha() {
        List<String> myList = Arrays.asList("one", "two", "three", "four", "", "", "");
        
        List<String> result = myList.stream()
                .map(value -> callSlowGithub())
                //.peek(System.out::println)
              
                .collect(Collectors.toList());
        
        System.out.println(result);
    }
}
