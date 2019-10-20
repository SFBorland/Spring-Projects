package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.addandreviewprice.response;

import com.rccl.digital.commerce.compositeaddandreviewprice.model.api.reviewprice.hybris.response.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payload {
    
    private CartItemResponse cartItem;
    private List<Price> prices;
    private BigDecimal subtotal;
}
