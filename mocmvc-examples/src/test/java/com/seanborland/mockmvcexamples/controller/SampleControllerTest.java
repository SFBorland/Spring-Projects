package com.seanborland.mockmvcexamples.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class SampleControllerTest {
    
    @InjectMocks
    SampleController sampleController;
    
    private MockMvc mockMvc;
    
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(sampleController)
                .build();
    }
    
    
    public void alpha() {
        mockMvc.perform(MockMvcRequestBuilders.get("/getPlayer"))
                .andExpect(MockM)
    }
}
