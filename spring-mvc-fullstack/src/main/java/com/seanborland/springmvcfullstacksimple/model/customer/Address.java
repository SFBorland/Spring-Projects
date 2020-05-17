package com.seanborland.springmvcfullstacksimple.model.customer;

import lombok.Data;

@Data
public class Address {
    
    private String street;
    private String city;
    private String state;
    private int zipCode;
}
