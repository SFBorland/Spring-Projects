package com.seanborland.reactivespringexamples.controllerexamples.repository;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
public class TWOFooRepositoryImplTest {
    
    @Test
    public void test() throws IOException {
        
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody("Hi from sean.")
                .addHeader("Content-Type", "application/json"));
        mockWebServer.start();
        
        UseBuilder useBuilder = new UseBuilder("http://localhost:" + mockWebServer.getPort());
        String result = useBuilder.clientCall().block();
        
        System.out.println("*********" + result);
    }
}
