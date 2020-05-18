package com.seanborland.springehcacheexample.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class JdbcService {
    
    JdbcTemplate jdbcTemplate;
    
    public JdbcService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public String getCountryByCountryCode(String countryCode) {
        Instant start = Instant.now();
        String result = jdbcTemplate.queryForObject(
                "SELECT name FROM country where code=" + countryCode, String.class);
        Instant finish = Instant.now();
        Duration totaltime = Duration.between(start, finish);
        
        System.out.println("Total time taken: " + totaltime.toMillis() + "ms");
        return result;
    }
    
    public List<City> getAllCities() {
        String sql = "SELECT * FROM city";
        
        Instant start = Instant.now();
        List<City> cities = jdbcTemplate.query(sql, (rs, rn) ->
                new City(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("CountryCode"),
                        rs.getString("District"),
                        rs.getString("Info")));
        Instant finish = Instant.now();
        long totalTime = Duration.between(start, finish).toMillis();
        System.out.println("Time take: " + totalTime + "ms");
        
        return cities;
    }
}
