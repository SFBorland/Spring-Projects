package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.cart.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemGuest {
    
    private String id;
    private BookingError inCartMessage;
    private String passengerId;
}
