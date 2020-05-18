package com.seanborland.mockitospring.repository;

import org.springframework.stereotype.Repository;

@Repository
public class DatabaseConnectionRepository implements DatabaseConnection {
    
    public String getConnection() {
        return getConnectedText();
    }
    
    private String getConnectedText() {
        return "Connected";
    }
}
