package com.seanborland.springmvcecommercesimple.model.customer;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Customer {
    private UUID uuid = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private String email;
    private Address billingAddress;
    private Address shippingAddress;
}
