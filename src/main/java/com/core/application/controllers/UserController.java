package com.core.application.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.domain.entities.User;
import com.core.domain.repository.UserRepository;
import com.core.errors.GlobalExceptionHandler;
import com.core.services.SuapAPI;
import com.core.services.entidies.TokenObject;
import com.core.services.entidies.UserAuthentication;


@RestController
public class UserController extends GlobalExceptionHandler{
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    SuapAPI requestToken;
    
    @PostMapping("/authentication/token/")
    public TokenObject createCustomer(@RequestBody UserAuthentication userAuthentication) {
        return requestToken.authentication(userAuthentication);
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/user")
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{uuid}")
    public User listUser(@PathVariable(value = "uuid") UUID uuid) {
        return userRepository.findById(uuid).get();
    }
}
