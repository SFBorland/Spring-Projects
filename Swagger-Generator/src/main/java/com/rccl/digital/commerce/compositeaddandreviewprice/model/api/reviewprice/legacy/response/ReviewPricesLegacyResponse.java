package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.reviewprice.legacy.response;

import com.rccl.digital.commerce.compositeaddandreviewprice.errors.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPricesLegacyResponse {
    
    private int status;
    private List<ErrorMessage> errors = new ArrayList<>();
    private List<LegacyProduct> products = new ArrayList<>();
}
