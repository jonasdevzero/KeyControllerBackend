package com.core.application.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.domain.entities.user.User;
import com.core.domain.repository.user.UserRepository;

@RestController
@RequestMapping(value = "/api")
public class CreateUserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    public User saveUser(@RequestBody User user){
        return userRepository.save(user);
    }
}
