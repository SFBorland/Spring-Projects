package com.seanborland.springmvcjpah2.repository;

import com.seanborland.springmvcjpah2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByProductName(String productName);
}
