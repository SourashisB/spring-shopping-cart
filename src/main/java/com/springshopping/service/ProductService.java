package com.springshopping.service;

import com.springshopping.entities.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

public interface ProductService {
    
    Product createProduct(Product product);
    Optional<Product> getProductById(Long id);
    List<Product> getProductsByName(String name);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    Product createOrUpdateProduct(Product product);
    
    
}
