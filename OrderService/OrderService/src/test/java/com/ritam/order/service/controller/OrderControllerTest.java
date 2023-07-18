package com.ritam.order.service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ritam.order.service.entities.Order;
import com.ritam.order.service.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @MockBean
    OrderService orderService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllOrders() throws Exception {

        List<Order> orderList = Arrays.asList(Order.builder().orderId("dummy1").productId("dummy1")
                .userId("dummy1").build(), Order.builder().orderId("dummy2").productId("dummy2")
                .userId("dummy2").build());
        Mockito.when(orderService.getAllOrders()).thenReturn(orderList);

        mockMvc.perform(MockMvcRequestBuilders.get("/order")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value("dummy1"));


    }

    @Test
    void getSingleOrder() throws Exception {
        Order order = Order.builder().orderId("dummy").productId("dummy")
                .userId("dummy").build();
        Mockito.when(orderService.getUserOrders("dummy")).thenReturn(List.of(order));

        mockMvc.perform(MockMvcRequestBuilders.get("/order/{userId}", "dummy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value("dummy"));
    }

    @Test
    void createOrder() throws Exception {
        Order order = Order.builder().orderId("dummy").productId("dummy")
                .userId("dummy").build();
        Mockito.when(orderService.createOrder(order)).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders.post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(order)))
                .andExpect(status().isCreated());
    }
}