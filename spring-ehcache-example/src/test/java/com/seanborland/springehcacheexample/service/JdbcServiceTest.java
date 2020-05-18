package com.seanborland.springehcacheexample.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JdbcServiceTest {
    
    @Autowired
    JdbcService jdbcService;
    
    @Test
    public void getCountryFromDb() {
        System.out.println("*** RESULT: " + jdbcService.getCountryByCountryCode("'USA'") + " ***");
    }
    
    @Test
    public void getCitiesFromDb() {
        System.out.println("*** RESULT: " + jdbcService.getAllCities().size() + " ***");
    }
}
