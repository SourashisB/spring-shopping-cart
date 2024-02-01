package main.java.com.springshopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springshopping.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

        
}
