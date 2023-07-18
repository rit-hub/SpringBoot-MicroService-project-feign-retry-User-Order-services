package com.ritam.user.service.service;

import com.ritam.user.service.entities.Order;
import com.ritam.user.service.entities.User;
import com.ritam.user.service.exception.ResourceNotFroundException;
import com.ritam.user.service.feign.OrderService;
import com.ritam.user.service.helper.UserDTO;
import com.ritam.user.service.repository.IUserRepositry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements IUserService{

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private IUserRepositry iUserRepositry;

    @Autowired
    private OrderService orderService;
    @Override
    public List<User> getAllUserService() {
        return iUserRepositry.findAll();
    }

    @Override
    public User createUser(User user) {
        String randomUId = UUID.randomUUID().toString();
        user.setId(randomUId);
        return iUserRepositry.save(user);
    }

    @Override
    public UserDTO getSingleUser(String userId) throws ResourceNotFroundException{
        User u = iUserRepositry.findById(userId).orElseThrow(()-> new ResourceNotFroundException("User Not Exixts"));
            List<Order> orderList= orderService.getOrder(userId);
            logger.info(u.toString());
            UserDTO userDto = UserDTO.builder().id(u.getId()).name(u.getName()).email(u.getEmail()).build();
            userDto.setOrderList(orderList);
            return userDto;

    }
}
