package main.java.com.springshopping.service;

import main.java.com.springshopping.repositories.OrderRepository;
import main.java.com.springshopping.service.OrderService;
import com.springshopping.entities.Order;
import com.springshopping.entities.OrderStatus;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    
}
