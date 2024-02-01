package main.java.com.springshopping.service;

import com.springshopping.entities.Product;

import main.java.com.springshopping.repositories.ProductRepository;
import main.java.com.springshopping.service.ProductService;

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

    public List<Product> getProductsByName(String name) {
        // This method should interact with the repository to find products with the given name.
        // The actual implementation depends on your data access layer.
        // For example, if you are using Spring Data JPA, it might look like this:
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}
