package com.seanborland.ehcachespringboot.controller;

import com.seanborland.ehcachespringboot.service.NumberService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NumberControllerTest {
    
    NumberService numberService = new NumberService();
    
    @Test
    public void alpha() {
        numberService.square(11L);
    }
}
