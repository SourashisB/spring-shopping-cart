package main.java.com.springshopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springshopping.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

    
    
}
