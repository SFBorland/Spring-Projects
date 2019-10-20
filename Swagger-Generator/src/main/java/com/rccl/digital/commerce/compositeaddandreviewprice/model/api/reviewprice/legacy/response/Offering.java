package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.reviewprice.legacy.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offering {
    
    private String offeringID;
    private String offeringDate;
    private String offeringTime;
    private OfferingUnitPrice offeringUnitPrice;
    private OfferingTotalPrice offeringTotalPrice;
    private boolean inventoryAvailable;
}
