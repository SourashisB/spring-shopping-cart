package main.java.com.springshopping.controllers;

import java.util.List;

import com.springshopping.entities.Product;
import main.java.com.springshopping.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> createOrUpdateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id); // Ensure the product has the ID passed in the path
        Product savedProduct = productService.createOrUpdateProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name) {

        if (id != null && name != null) {
            return ResponseEntity.badRequest()
                    .body("Please provide either an ID or a name to search for a product, not both.");
        }

        if (id != null) {
            // Search by product ID
            return productService.getProductById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else if (name != null) {
            // Search by product name
            List<Product> products = productService.getProductsByName(name);
            if (products.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(products);
            }
        } else {
            return ResponseEntity.badRequest().body("Please provide an ID or a name to search for a product.");
        }
    }
}
