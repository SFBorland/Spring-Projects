package com.seanborland.springmvcjpah2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories//(basePackages = "com.seanborland.springmvcjpah2.repository")
@SpringBootApplication
public class SpringMvcJpaH2Application {
    
    public static void main(String[] args) {
        SpringApplication.run(SpringMvcJpaH2Application.class, args);
    }
    
}
