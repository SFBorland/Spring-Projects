package com.seanborland.springresttemplateexamples.interceptors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BasicInterceptorExampleTest {
    
    BasicInterceptorExample basicInterceptorExample = new BasicInterceptorExample();
    
    @Test
    public void alpha() {
        String result = basicInterceptorExample.callInterceptedAndLogged();
    
        System.out.println(result);
    }
    
    @Test
    public void bravo() {
        String result = basicInterceptorExample.callInterceptedAndHeadersAdded();
        
        System.out.println(result);
    }
}
