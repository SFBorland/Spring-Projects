package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.addandreviewprice.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class AddAndReviewPriceRequest {
    
    @ApiModelProperty(example = "en", required = true, position = 1)
    private String locale;
    @ApiModelProperty(example = "royal", required = true, position = 1)
    private String brand;
    @ApiModelProperty(example = "mobile", required = true, position = 1)
    private String platform;
    @ApiModelProperty(example = "507f1f77bcf86cd799439011", required = true, position = 1)
    private String reqIdHeader;
    @ApiModelProperty(example = "123456", required = true, position = 2)
    private String cruiseBookingID;
    @ApiModelProperty(example = "AL20190101", required = true, position = 3)
    private String sailingID;
    @ApiModelProperty(example = "USD", required = true, position = 4)
    private String currency;
    @ApiModelProperty(example = "632415", required = true, position = 5)
    private String signOnGuestID;
    @ApiModelProperty(example = "some-device-id", required = true, position = 6)
    private String deviceID;
    @ApiModelProperty(example = "54646-54471", required = true, position = 7)
    private String cartId;
    @ApiModelProperty(example = "XZ17", required = true, position = 8)
    private Product product;
    @ApiModelProperty(example = "[65464,65464,654654]", required = true, position = 9)
    private List<Guest> guests;
    @ApiModelProperty(example = "[]", required = true, position = 10)
    private List<Filter> filters;
}
