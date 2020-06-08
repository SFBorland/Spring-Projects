package com.seanborland.springmvcpersistence.repository;

import org.junit.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourceExamples {
    
    /**
     * This example uses Springs DataSource implementation. DataSource is an interface.
     *
     */
    @Test
    public void simpleDataSourceExample() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/world");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        
        Connection connection = dataSource.getConnection();
        
        try(Statement statement = connection.createStatement()) {
            
            ResultSet resultSet = statement.executeQuery("SELECT * FROM city LIMIT 10");
            
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("Name"));
            }
        }
    }
    
    @Test
    public void alpha() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:test");
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
        dataSourceBuilder.build();
        
    }
}
