package com.seanborland.mockitospring.service;

import com.seanborland.mockitospring.repository.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseService {
    
    private final DatabaseConnection databaseConnection;
    
    @Autowired
    public EnterpriseService(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    
    String getSomething() {
        
        String connection = databaseConnection.getConnection();
    
        return "DB Status: " + connection;
    }
}
