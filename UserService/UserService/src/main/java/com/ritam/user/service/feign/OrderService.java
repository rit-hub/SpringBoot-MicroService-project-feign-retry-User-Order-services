package com.ritam.user.service.feign;

import com.ritam.user.service.entities.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderService {
    @GetMapping("/order/{userId}")
    List<Order> getOrder(@PathVariable String userId);
}
