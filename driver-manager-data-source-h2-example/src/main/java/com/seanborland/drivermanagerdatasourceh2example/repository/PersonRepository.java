package com.seanborland.drivermanagerdatasourceh2example.repository;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Slf4j
@Repository
public class PersonRepository {
    
    public ResultSet connectUsingDriverManager() {
        
        Connection connection;
        ResultSet resultSet = null;
        
        try {
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "password");
            Statement statement = connection.createStatement();
            
            String sql = "SELECT * FROM city where population > 1000000";
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet connectUsingDataSource() {
        
        MysqlDataSource dataSource = new MysqlDataSource();
        
        try {
            Connection connection = dataSource.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public void getPerson() {
//        String sql = "SELECT * FROM PERSON where id=1001";
//
//        List<Person> personList = jdbcTemplate.query(sql, (rs, rowNum) -> new Person(
//                rs.getLong("id"), rs.getString("firstName"),
//                rs.getString("lastName"), rs.getDate("dob")));
//
//        personList.forEach(System.out::println);
//    }
}
