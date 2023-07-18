package com.ritam.order.service.service;

import com.ritam.order.service.entities.Order;
import com.ritam.order.service.exception.ResourceNotFoundException;
import com.ritam.order.service.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getUserOrders(String userId){
        return orderRepository.findByUserId(userId);
    }


    @Override
    public Order createOrder(Order order) {
        String randomUid = UUID.randomUUID().toString();
        order.setOrderId(randomUid);
        Order o = orderRepository.save(order);
        return o;
    }
}
