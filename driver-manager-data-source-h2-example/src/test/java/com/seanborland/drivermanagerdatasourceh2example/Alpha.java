package com.seanborland.drivermanagerdatasourceh2example;

import com.seanborland.drivermanagerdatasourceh2example.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Alpha {
    
    @Autowired
    PersonRepository personRepository;
    
    @Test
    public void connectUsingDriverManager() throws SQLException {
        
        ResultSet resultSet = personRepository.connectUsingDriverManager();
        
        while (resultSet.next()) {
            
            System.out.println(resultSet.getString(2));
        }
    }
}
