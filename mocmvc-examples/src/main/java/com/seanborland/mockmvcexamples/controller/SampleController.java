package com.seanborland.mockmvcexamples.controller;

import com.seanborland.mockmvcexamples.model.Player;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping
public class SampleController {
    
    @GetMapping("/getPlayer")
    public Player getPlayer() {
        
        return new Player("Sean Borland", "Manchester City", 8);
    }
}
