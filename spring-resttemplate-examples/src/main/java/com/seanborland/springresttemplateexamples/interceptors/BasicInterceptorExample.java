package com.seanborland.springresttemplateexamples.interceptors;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

public class BasicInterceptorExample {
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    public String callInterceptedAndLogged() {
        restTemplate.setInterceptors(Collections.singletonList(new UriPrinterInterceptor()));
        ResponseEntity<String> s = restTemplate.exchange("http://www.google.com", HttpMethod.GET, null, String.class);
        System.out.println(s.getStatusCode());
        
        return "Dwigt!";
    }
    
    public String callInterceptedAndHeadersAdded() {
        restTemplate.setInterceptors(Collections.singletonList(new HeaderAddingInterceptor()));
        restTemplate.exchange("http://www.google.com", HttpMethod.GET, null, String.class);
        
        return "Dwigt!";
    }
}

class UriPrinterInterceptor implements ClientHttpRequestInterceptor {
    
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes,
                                        ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
    
        System.out.println("***********CALL INTERCEPTED***********");
        System.out.println("*********** URI CALLED: " + httpRequest.getURI());
        
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}

class HeaderAddingInterceptor implements ClientHttpRequestInterceptor {
    
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes,
                                        ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        
        System.out.println("***********CALL INTERCEPTED***********");
        httpRequest.getHeaders().add("you're", "intercepted!");
        System.out.println(httpRequest.getHeaders());
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
/**
 *
 * Work sample, names changed.
 *
 * @Override
 *     public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
 *     throws IOException {
 *
 *         request.getHeaders().add(FOO_HEADER, this.barStore.getTraceId().toString());
 *
 *         return execution.execute(request, body);
 *     }
 */
