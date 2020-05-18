package com.seanborland.reactivespringexamples.controllerexamples.repository;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@AutoConfigureWebTestClient
@RunWith(MockitoJUnitRunner.class)
public class FooRepositoryImplTest {
    
    @Autowired
    WebTestClient webTestClient;
    
    private MockWebServer mockWebServer = new MockWebServer();
//    @Mock
//    WebClient webClient = WebClient.create(mockWebServer.url("/"));
//    WebClient.create(mockWebServer.url("/").toString())

    @Mock
    WebClient webClient;// = WebClient.create("/");
    
    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    WebClient.ResponseSpec responseSpec;
    
    @InjectMocks
    UseBuilder fooRepository;
    
//    @Before
//    public void setUp() throws IOException {
//        mockWebServer.url("http://www.google.com");
//        mockWebServer.enqueue(getMockResponse());
//        //mockWebServer.start();
//    }
    
    @Test
    public void alpha() throws InterruptedException {
        
        mockWebServer.takeRequest();
        //when(fooRepository.clientCall()).thenReturn(Mono.just("I'm just a mock :("));
       // when(WebClient.create("http://www.google.com")).thenReturn(WebClient.create("/"));
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("Sean"));
        
        String bar = fooRepository.getBar().block();
        
        assertEquals("sean test 123", bar);
        
    }
    
    private MockResponse getMockResponse() {
        return new MockResponse().setBody("sean test 123");
    }
}
