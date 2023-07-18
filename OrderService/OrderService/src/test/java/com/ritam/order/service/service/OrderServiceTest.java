package com.ritam.order.service.service;

import com.ritam.order.service.entities.Order;
import com.ritam.order.service.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepositoryTest;
    private AutoCloseable autoCloseable;
    private OrderService orderServiceTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        orderServiceTest = new OrderService(orderRepositoryTest);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllOrders() {
        orderServiceTest.getAllOrders();
        Mockito.verify(orderRepositoryTest).findAll();
    }

    @Test
    void getUserOrders() {
        orderServiceTest.getUserOrders("123");
        Mockito.verify(orderRepositoryTest).findByUserId("123");
    }

    @Test
    void createOrder() {
        Order order = Order.builder().orderId("123").userId("123").productId("123").build();
        orderServiceTest.createOrder(order);

        ArgumentCaptor<Order> argumentCaptor = ArgumentCaptor.forClass(Order.class);
        Mockito.verify(orderRepositoryTest).save(argumentCaptor.capture());

        Order capturedOrder = argumentCaptor.getValue();
        assertThat(capturedOrder).isEqualTo(order);

    }
}