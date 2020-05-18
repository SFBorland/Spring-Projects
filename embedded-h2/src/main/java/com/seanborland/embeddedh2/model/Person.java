package com.seanborland.embeddedh2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Person {
    
    private long id;
    private String firstName;
    private String lastName;
    private Date dob;
}
