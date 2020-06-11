package com.seanborland.springmvcjpah2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    
    @Id
    @GeneratedValue
    private Long productId;
    private String productName;
    private String modelNumber;
}
