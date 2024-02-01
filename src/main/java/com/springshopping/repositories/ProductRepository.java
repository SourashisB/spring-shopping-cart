package com.springshopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springshopping.entities.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>{

    
    public List<Product> findByName(String name);
}
