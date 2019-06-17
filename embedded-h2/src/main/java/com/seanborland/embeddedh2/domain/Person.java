package com.seanborland.embeddedh2.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Person {
    
    private long id;
    private String firstName;
    private String lastName;
    private Date dob;
}
