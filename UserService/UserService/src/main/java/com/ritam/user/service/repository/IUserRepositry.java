package com.ritam.user.service.repository;

import com.ritam.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepositry extends JpaRepository<User,String> {

}
