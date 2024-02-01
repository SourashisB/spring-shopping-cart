package com.springshopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springshopping.entities.Order;
import com.springshopping.entities.OrderStatus;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

     public List<Order> findByStatus(OrderStatus status);
}
