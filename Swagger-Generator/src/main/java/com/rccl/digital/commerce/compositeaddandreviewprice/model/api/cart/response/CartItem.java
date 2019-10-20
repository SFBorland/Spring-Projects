package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.cart.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    
    private String id;
    private BookingError inCartMessage;
    private List<CartItemGuest> guests = new ArrayList<>();
}
