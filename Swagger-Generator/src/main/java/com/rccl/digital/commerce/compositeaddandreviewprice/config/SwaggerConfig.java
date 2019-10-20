package com.rccl.digital.commerce.compositeaddandreviewprice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalTime;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.directModelSubstitute(LocalTime.class, java.util.Date.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rccl.digital.commerce" +
                        ".compositeaddandreviewprice.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
