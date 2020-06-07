package com.seanborland.springmvcpersistence.repository;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverManagerExamples {
    
    /**
     * The try-with-resources statement is a try statement that declares one or more resources. A resource is an object
     * that must be closed after the program is finished with it. The try-with-resources statement ensures that each
     * resource is closed at the end of the statement. Any object that implements java.lang.AutoCloseable, which
     * includes all objects which implement java.io.Closeable, can be used as a resource.
     */
    @Test
    public void simpleDriverManagerExample() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root",
                "password");
        try(Statement statement = connection.createStatement()) {
            
            ResultSet resultSet = statement.executeQuery("SELECT * FROM city LIMIT 10");
            
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
            }
        }
    }
    
    @Test
    public void simpleDriverManagerExampleUsingTryWithResources() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root",
                    "password");
            
            Statement statement = connection.createStatement();
            
            ResultSet resultSet = statement.executeQuery("SELECT * FROM city LIMIT 10");
            
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
            }
            connection.close();
        } catch (Exception e) {
            System.err.println("Something went wrong!");
            e.printStackTrace();
        }
    }
}
