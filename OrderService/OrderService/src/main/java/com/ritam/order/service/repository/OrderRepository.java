package com.ritam.order.service.repository;

import com.ritam.order.service.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {
    //custom finder methods
    List<Order> findByUserId(String userId);

}
