package com.rccl.digital.commerce.compositeaddandreviewprice.model.api.addandreviewprice.response;

import com.rccl.digital.commerce.compositeaddandreviewprice.errors.ErrorMessage;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAndReviewPriceResponse {
    
    @ApiModelProperty(example = "200", required = true, position = 1)
    private int status = 200;
    
    @ApiModelProperty(example = "[]", position = 2)
    private List<String> errors = new ArrayList<>();
    
    @ApiModelProperty(required = true, position = 3)
    private Payload payload;
}
