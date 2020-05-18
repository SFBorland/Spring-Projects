package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.cart.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingError {
    
    private String code;
    private int conflictBookingId;
    private String constraint;
}
