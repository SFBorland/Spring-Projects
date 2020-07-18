package com.seanborland.springmvcjpah2.repository;

import com.seanborland.springmvcjpah2.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDataLoader {
    
    private final ProductRepository productRepository;
    
    @Autowired
    public ProductDataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @PostConstruct
    public void loadProducts() {
        List<Product> products = getProducts();
        productRepository.saveAll(products);
    }
    
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "ForemanGrill", "GFG-123"));
        products.add(new Product(2L, "PeletonBike", "PEL-999"));
        return products;
    }
}
