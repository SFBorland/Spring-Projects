package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.addandreviewprice.request;

import lombok.Data;

@Data
public class Product {
    private String productID;
    private String productCode;
    private String offeringId;
    private int quantity;
}
