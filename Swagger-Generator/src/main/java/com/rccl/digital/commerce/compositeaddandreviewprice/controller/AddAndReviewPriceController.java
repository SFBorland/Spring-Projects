package com.rccl.digital.commerce.compositeaddandreviewprice.controller;

import com.rccl.digital.commerce.compositeaddandreviewprice.model.api.addandreviewprice.request.AddAndReviewPriceRequest;
import com.rccl.digital.commerce.compositeaddandreviewprice.model.api.addandreviewprice.response.AddAndReviewPriceResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{locale}/{brand}/{platform}")
public class AddAndReviewPriceController {
    
    @PostMapping("/addAndReviewPrice")
    public AddAndReviewPriceResponse cont(@RequestBody AddAndReviewPriceRequest addAndReviewPriceRequest) {
        return new AddAndReviewPriceResponse();
    }
}
