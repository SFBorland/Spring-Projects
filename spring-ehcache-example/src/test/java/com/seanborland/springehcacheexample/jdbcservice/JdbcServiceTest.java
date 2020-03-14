package com.seanborland.springehcacheexample.jdbcservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JdbcServiceTest {
    
    @Autowired
    JdbcService jdbcService;
    
    @Test
    public void alpha() {
        System.out.println("*** RESULT: " + jdbcService.getCountry() + " ***");
    }
}
