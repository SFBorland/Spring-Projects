package com.seanborland.springmvcfullstacksimple.model.customer;

import lombok.Data;

import java.util.UUID;

@Data
public class Address {
    
    private UUID uuid;
    private String street;
    private String city;
    private String state;
    private int zipCode;
}
