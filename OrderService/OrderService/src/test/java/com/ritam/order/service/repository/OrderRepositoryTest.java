package com.ritam.order.service.repository;

import com.ritam.order.service.entities.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryTest {
    @Autowired
    private OrderRepository testOrderRepo;

    @AfterEach
    void tearDown(){
        testOrderRepo.deleteAll();

    }
    @Test
    void testIfExixtsFindByUserId() {
        String uid = "123";
        Order order = Order.builder().orderId("123").userId(uid).productId("123").build();
        testOrderRepo.save(order);

        List<Order> expected = testOrderRepo.findByUserId(uid);

        assertThat(expected.get(0)).isEqualTo(order);
    }
    @Test
    void testIfDoesNotExixtsFindByUserId(){
        String uid = "123";

        Order order = Order.builder().orderId("123").userId(uid).productId("123").build();

        List<Order> expected = testOrderRepo.findByUserId(uid);
        assertThat(expected).isEqualTo(new ArrayList<>());
    }


}