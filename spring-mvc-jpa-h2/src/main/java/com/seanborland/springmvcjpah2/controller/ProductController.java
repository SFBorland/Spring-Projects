package com.seanborland.springmvcjpah2.controller;

import com.seanborland.springmvcjpah2.model.Product;
import com.seanborland.springmvcjpah2.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ProductController {
    
    private final ProductRepository productRepository;
    
    @GetMapping("/products/{productName}")
    public Product getAllProducts(@PathVariable String productName) {
        System.out.println("HERE");
        return productRepository.findProductByProductName(productName);
    }
}
