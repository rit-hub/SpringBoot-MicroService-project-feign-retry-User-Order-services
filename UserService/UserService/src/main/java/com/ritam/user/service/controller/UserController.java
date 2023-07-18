package com.ritam.user.service.controller;

import com.ritam.user.service.entities.User;
import com.ritam.user.service.helper.UserDTO;
import com.ritam.user.service.service.UserServiceImpl;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    // get all user
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUser = userService.getAllUserService();
        return ResponseEntity.ok(allUser);
    }
    // create user

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody  User user){
        User  u = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);

    }
    @GetMapping("/{userId}")
    @Retry(name = "userOrderService", fallbackMethod = "userOrderFallback")
    public ResponseEntity<UserDTO> getUser(@PathVariable String userId){
        return ResponseEntity.ok(userService.getSingleUser(userId));
    }

    public  ResponseEntity<UserDTO> userOrderFallback(String userId, Exception ex){
        logger.info("Fallback Method called due to server down!!!");
        UserDTO u = UserDTO.builder().email("dummy@gmail.com").name("Dummy Name").id("dummy id").build();
        return new ResponseEntity<>(u, HttpStatus.BAD_REQUEST);
    }


}
