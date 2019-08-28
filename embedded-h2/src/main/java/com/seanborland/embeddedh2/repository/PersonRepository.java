package com.seanborland.embeddedh2.repository;

import com.seanborland.embeddedh2.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public void getPerson() {
        String sql = "SELECT * FROM PERSON where id=1001";
        
        List<Person> personList = jdbcTemplate.query(sql, (rs, rowNum) -> new Person(
            rs.getLong("id"), rs.getString("firstName"),
                    rs.getString("lastName"), rs.getDate("dob")));
    
        personList.forEach(System.out::println);
    }
}
