package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.cart.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    
    private int status;
    private Error error;
    private String warnings;
    private CartItem payload;
}
