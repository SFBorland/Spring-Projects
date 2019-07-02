package com.seanborland.reactivespringexamples.webclientexamples;


import lombok.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class OfferingRequest {


    private String shipCode = "AL";

    private String sailDate = "20190630";


    private String productCode = "ADAGDINNER";


    private List<String> days = Collections.singletonList("20190704");


    private String numberOfGuests="2";

    private String vipStatus="0";
}

