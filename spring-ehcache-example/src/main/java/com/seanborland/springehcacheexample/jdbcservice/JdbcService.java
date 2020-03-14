package com.seanborland.springehcacheexample.jdbcservice;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcService {
    
    JdbcTemplate jdbcTemplate;
    
    public JdbcService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public String getCountry() {
        return jdbcTemplate.queryForObject(
                "SELECT name FROM country where code='USA'", String.class);
    }
}
