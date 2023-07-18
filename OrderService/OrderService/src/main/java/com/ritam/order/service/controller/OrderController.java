package com.ritam.order.service.controller;


import com.ritam.order.service.entities.Order;
import com.ritam.order.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getSingleOrder(@PathVariable String userId){
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
    }
}
