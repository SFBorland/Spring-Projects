package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.addandreviewprice.request;

import lombok.Data;

@Data
public class Filter {
    
    private String filterKey;
    private String filterType;
    private String filterValue;
}
