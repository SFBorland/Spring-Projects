package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.reviewprice.hybris.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPriceResponse {
    
    private int status;
    private Error error;
    private String warning;
    private Payload payload;
}
