package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.reviewprice.legacy.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferingTotalPrice {
    
    private String currency;
    private BigDecimal adultPrice;
    private BigDecimal childPrice;
    private BigDecimal infantPrice;
    private BigDecimal grandTotalPrice;
}
