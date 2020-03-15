package com.seanborland.springehcacheexample.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    
    private int id;
    private String name;
    private String countryCode;
    private String district;
    private String info;
}
