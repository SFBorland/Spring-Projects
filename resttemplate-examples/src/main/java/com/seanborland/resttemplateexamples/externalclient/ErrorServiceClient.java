package com.seanborland.resttemplateexamples.externalclient;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ErrorServiceClient {
    
    private final RestTemplate restTemplate;

    @Autowired
    public ErrorServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Test
    public void callErrorService() {
    String uri = "http://shiptst1.mesos.rccl.com/error-messages/v1/read/errors?locale=en&errorCode=request.parameters" +
            ".invalid";

    ErrorResponse errorResponse = restTemplate.getForObject(uri, ErrorResponse.class);

        System.out.println(errorResponse.getErrorTitle());
    }

}

@Data
class ErrorResponse {

    String errorCode;
    String errorTitle;
    String errorMessage;
}
