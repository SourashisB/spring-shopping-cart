package main.java.com.springshopping.service;

import main.java.com.springshopping.repositories.OrderRepository;
import main.java.com.springshopping.service.OrderService;
import com.springshopping.entities.Order;
import com.springshopping.entities.OrderStatus;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrderServiceImplementation implements OrderService{

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImplementation(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Transactional
    @Override
    public Order createOrder(Order order) {

        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    @Override
    public Order updateOrder(Long id, Order orderDetails) {
        // Fetch, update and save the order entity
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));

        // Update fields
        order.setStatus(orderDetails.getStatus());
        // ... other fields ...

        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }
    
}
