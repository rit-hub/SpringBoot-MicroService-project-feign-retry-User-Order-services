package com.ritam.user.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ritam.user.service.entities.Order;
import com.ritam.user.service.entities.User;
import com.ritam.user.service.helper.UserDTO;
import com.ritam.user.service.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {


    @MockBean
    UserServiceImpl userServiceTest;

    @Autowired
    MockMvc mockMvc;



    @Test
    @DisplayName("Should return the user when the user id exists")
    void getUserWhenUserIdExists() {
        String userId = "123";
        UserDTO userDTO = UserDTO.builder()
                .id(userId)
                .name("John Doe")
                .email("john.doe@example.com")
                .orderList(Arrays.asList(new Order("1", "123", "456")))
                .build();

        Mockito.when(userServiceTest.getSingleUser(userId)).thenReturn(userDTO);

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}", userId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(userId))
                    .andExpect(jsonPath("$.name").value("John Doe"))
                    .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                    .andExpect(jsonPath("$.orderList[0].orderId").value("1"))
                    .andExpect(jsonPath("$.orderList[0].productId").value("123"))
                    .andExpect(jsonPath("$.orderList[0].userId").value("456"));
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void getAllUsers() throws Exception {
        List<User> userList = Arrays.asList(User.builder()
                        .id("dummy1").name("dummy1").email("dummy1@gmail.com").build(),
                User.builder()
                        .id("dummy2").name("dummy2").email("dummy2@gmail.com").build());

        Mockito.when(userServiceTest.getAllUserService()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("dummy1"));
    }

    @Test
    void createUser() throws Exception {
        User user = User.builder()
                .id("dummy1").name("dummy1").email("dummy1@gmail.com").build();
        Mockito.when(userServiceTest.createUser(user)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated());

    }
}