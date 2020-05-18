package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.addandreviewprice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    
    private String id;
    private List<CartItemGuestResponse> guests = new ArrayList<>();
}
