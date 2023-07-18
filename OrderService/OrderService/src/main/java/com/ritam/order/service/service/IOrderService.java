package com.ritam.order.service.service;

import com.ritam.order.service.entities.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAllOrders();
    List<Order> getUserOrders(String userId);
    Order createOrder(Order order);
}
