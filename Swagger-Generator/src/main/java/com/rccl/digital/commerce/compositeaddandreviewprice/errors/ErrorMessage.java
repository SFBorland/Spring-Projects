package com.rccl.digital.commerce.compositeaddandreviewprice.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    
    private String errorCode;
    private String errorTitle;
    private String userMessage;
    private String developerMessage;
    private String internalMessage;
    private String moreInfo;
}
