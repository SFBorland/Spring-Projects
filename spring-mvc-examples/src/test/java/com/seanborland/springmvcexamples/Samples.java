package com.seanborland.springmvcexamples;

import org.junit.Test;
import org.springframework.web.util.UriComponentsBuilder;

public class Samples {
    @Test
    public void alpha() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/itinerary");
        builder.queryParam("sailingId", "x")
                .queryParam("reservationId", "123")
                .queryParam("foo", "bar")
                .build();
        
        System.out.println(builder.toUriString());
    }
}
