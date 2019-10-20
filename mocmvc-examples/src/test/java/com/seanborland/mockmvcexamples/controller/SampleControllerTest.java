package com.seanborland.mockmvcexamples.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest
@AutoConfigureMockMvc
class SampleControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @Before
    public void setUp() {
    
    }
    
    @Test
    public void alpha() throws Exception {

//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(sampleController)
//                .build();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/getPlayer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Sean Borland")));
    }
}
