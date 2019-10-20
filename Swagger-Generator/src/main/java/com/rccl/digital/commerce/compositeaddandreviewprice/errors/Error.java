package com.rccl.digital.commerce.compositeaddandreviewprice.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    
    private String errorCode;
    private String message;
    private String moreInfo;
}
