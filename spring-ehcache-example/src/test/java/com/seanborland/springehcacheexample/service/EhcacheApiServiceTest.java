package com.seanborland.springehcacheexample.service;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@SpringBootTest
public class EhcacheApiServiceTest {
    
    @Autowired
    JdbcService jdbcService;
    
    @Test
    public void alpha() {
        
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("preConfigured",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                                ResourcePoolsBuilder.heap(100))
                                .build())
                .build(true);
        
        Cache<Long, String> preConfigured
                = cacheManager.getCache("preConfigured", Long.class, String.class);
        
        Cache<Long, String> myCache = cacheManager.createCache("myCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                        ResourcePoolsBuilder.heap(100)).build());
        
        myCache.put(1L, "da one!");
        String value = myCache.get(1L);
        
        System.out.println(value);
        cacheManager.close();
    }
    
    @Test
    public void bravo() {
        
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("preConfigured",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, City.class,
                                ResourcePoolsBuilder.heap(100))
                                .build())
                .build(true);
        
        Cache<Integer, City> myCache = cacheManager.createCache("myCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, City.class,
                        ResourcePoolsBuilder.heap(4070)).build());
        
        List<City> cities = jdbcService.getAllCities();
        
        //cities.forEach(city -> myCache.put(city.getId(), city));
        
        for (City city : cities) {
            myCache.put(city.getId(), city);
        }
        
        System.out.println("cache value: " + myCache.get(4080));
        cacheManager.close();
    }
}
