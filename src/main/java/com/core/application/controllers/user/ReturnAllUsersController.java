package com.core.application.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.domain.entities.user.User;
import com.core.domain.repository.user.UserRepository;

@RestController
@RequestMapping(value = "/api")
public class ReturnAllUsersController {
    @Autowired
    UserRepository userRepository;
    // GET METHODS
    @GetMapping("/user")
    public List<User> listUsers(){
        return userRepository.findAll();
    }
}
