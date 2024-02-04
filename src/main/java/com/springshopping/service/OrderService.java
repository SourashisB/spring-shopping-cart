package com.springshopping.service;

import com.springshopping.entities.Order;
import com.springshopping.entities.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    
    Order createOrder(Order order);
    Optional<Order> getOrderById(Long id);
    List<Order> getAllOrders();
    Order updateOrder(Long id, Order order);
    void deleteOrder(Long id);
    List<Order> getOrdersByStatus(OrderStatus status);
    Order createOrUpdateOrder(Order order);
}
