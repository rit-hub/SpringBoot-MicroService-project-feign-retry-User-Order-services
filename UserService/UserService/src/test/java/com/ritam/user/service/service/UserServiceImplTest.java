package com.ritam.user.service.service;

import com.ritam.user.service.entities.Order;
import com.ritam.user.service.entities.User;
import com.ritam.user.service.exception.ResourceNotFroundException;
import com.ritam.user.service.feign.OrderService;

import com.ritam.user.service.repository.IUserRepositry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private Logger logger;
    @Mock
    private OrderService orderService;
    @Mock
    private IUserRepositry userRepositryTest;
    private UserServiceImpl userServiceTest;

    @BeforeEach
    void setUp() {
        userServiceTest = UserServiceImpl.builder().iUserRepositry(userRepositryTest).build();
    }


    @Test
    void getAllUserService() {
        userServiceTest.getAllUserService();
        Mockito.verify(userRepositryTest).findAll();
    }

    @Test
    void createUser() {
        User user = User.builder().id("123").name("dummy").email("dummy@gmail.com")
                .build();
        userServiceTest.createUser(user);
        Mockito.verify(userRepositryTest).save(user);
    }
}