package com.seanborland.mockitospring.service;

import com.seanborland.mockitospring.repository.DatabaseConnection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnterpriseServiceTest {
    
    @Mock
    DatabaseConnection databaseConnection;
    
    @InjectMocks
    EnterpriseService enterpriseService;
    
    @Test
    public void TestGetSomething() {
        when(databaseConnection.getConnection()).thenReturn("Connected! Powered by Mockito.");
        Assert.assertEquals("DB Status: Connected! Powered by Mockito.", enterpriseService.getSomething());
    }
}
