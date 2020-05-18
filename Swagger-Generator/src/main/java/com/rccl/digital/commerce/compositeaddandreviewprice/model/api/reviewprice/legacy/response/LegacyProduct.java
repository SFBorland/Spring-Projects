package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.reviewprice.legacy.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class LegacyProduct {
    
    private String productID;
    private String productCode;
    private List<LegacyGuest> guests = new ArrayList<>();
    private List<Offering> offerings = new ArrayList<>();
}
