package com.springshopping.service;

import com.springshopping.entities.Product;

import com.springshopping.repositories.ProductRepository;
import com.springshopping.service.ProductService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class ProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImplementation (ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    
    @Transactional
    @Override
    public Product createProduct(Product product) {
        
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

        // Update fields
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        // ... other fields ...

        return productRepository.save(product);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product createOrUpdateProduct(Product product) {
        // Check if product already exists
        // If it does, update it
        // If not, save (create) it as a new product
        // Assuming Product has an id attribute that is used to check its existence
        if (productRepository.existsById(product.getId())) {
            // Update existing product
            // You could add more logic here to handle updates specifically
        }
        // Create new or update existing product
        return productRepository.save(product);
    }


}
