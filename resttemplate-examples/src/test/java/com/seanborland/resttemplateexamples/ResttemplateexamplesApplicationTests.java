package com.seanborland.resttemplateexamples;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResttemplateexamplesApplicationTests {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Test
    public void callErrorService() {
        String uri = "http://shiptst1.mesos.rccl.com/error-messages/v1/read/errors?locale=en&errorCode=request" +
                ".parameters" +
                ".invalid";
    
        ErrorResponse errorResponse;
        
        try {
         errorResponse = restTemplate.getForObject(uri, ErrorResponse.class);
         
        } catch (HttpServerErrorException ex) {
            log.error("AN ERROR OCCURRED: " + ex.getMessage());
            errorResponse = new ErrorResponse();
            
            errorResponse.setErrorTitle("foo");
        }
        
        System.out.println("RESULT: " + errorResponse.getErrorTitle());
    }
    
}

@Data
class ErrorResponse {
    
    String errorCode;
    String errorTitle;
    String errorMessage;
}
