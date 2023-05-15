package com.core.application.controllers.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.domain.entities.User;
import com.core.domain.repository.UserRepository;

@RestController
@RequestMapping(value = "/api")
public class ReturnSingleUserController {
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/user/{uuid}")
    public User listUser(@PathVariable (value= "uuid") UUID uuid){
        return userRepository.findById(uuid).get();
    }
}
