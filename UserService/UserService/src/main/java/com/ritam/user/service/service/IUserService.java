package com.ritam.user.service.service;

import com.ritam.user.service.entities.User;
import com.ritam.user.service.helper.UserDTO;

import java.util.List;

public interface IUserService {
    List<User> getAllUserService();
    User createUser(User user);

    UserDTO getSingleUser(String userId);
}
