package com.seanborland.vvmspringmvc;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SlowServiceClient {
    
    RestTemplate restTemplate = new RestTemplate();
    
    public String callSlowService(int sleepTime) {
        return restTemplate.getForObject("http://localhost:8082/slowService/" + sleepTime, String.class);
    }
}
