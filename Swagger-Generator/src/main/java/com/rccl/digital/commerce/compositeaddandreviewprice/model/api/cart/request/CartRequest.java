package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.cart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    
    private int quantity;
    private ProductSummary productSummary;
    private Offering offering;
    private List<Guest> guests;
}
