package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.reviewprice.hybris.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    
    private BigDecimal basePrice;
    private BigDecimal promoPrice;
    private String guestType;
    private String guestId;
}
