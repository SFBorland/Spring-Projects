package com.seanborland.reactivespringexamples.controllerexamples.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FooRepositoryImplTest {
    
//    @Mock
//    WebClient webClient;
//
//    @Mock
//    WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
//
//    @Mock
//    WebClient.ResponseSpec responseSpec;
    
    @InjectMocks
    FooRepositoryImpl fooRepository = new FooRepositoryImpl();
//    final var uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
//    final var headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
//    final var responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);
    
    @Test
    public void alpha() {
        
        //when(fooRepository.clientCall()).thenReturn(Mono.just("I'm just a mock :("));
//        when(WebClient.create("http://www.google.com")).thenReturn(webClient);
//        when(webClient.get()).thenReturn(requestHeadersUriSpec);
//        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
//        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("I'm just a mock :("));
        
        String bar = fooRepository.getBar();
        
    }
}
