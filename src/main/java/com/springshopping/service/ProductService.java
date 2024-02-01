package main.java.com.springshopping.service;

import com.springshopping.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    
    Product createProduct(Product product);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    
}
