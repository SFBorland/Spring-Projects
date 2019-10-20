package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.reviewprice.hybris.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payload {
    
    private List<Price> prices = new ArrayList<>();
    private BigDecimal subtotal;
}
