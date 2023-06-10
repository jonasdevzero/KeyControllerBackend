package com.core.application.controllers;

import java.net.URISyntaxException;

import com.core.domain.models.User;
import com.core.domain.dto.suap.SuapTokens;
import com.core.domain.models.UserType;
import com.core.infra.security.annotations.JwtAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import com.core.domain.repository.UserRepository;
import com.core.application.errors.GlobalExceptionHandler;
import com.core.infra.suap.SuapAPI;
import com.core.domain.dto.JWTObject;
import com.core.domain.dto.UserAuthentication;
import com.core.domain.dto.suap.SuapUser;
import com.core.infra.security.JWT;

@RestController
public class UserController extends GlobalExceptionHandler {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JWT jwt;
    @Autowired
    SuapAPI suapApi;

    @PostMapping("/user/login")
    public JWTObject auth(@RequestBody UserAuthentication userAuthentication) throws RestClientException, URISyntaxException {
        SuapTokens suapTokens = suapApi.login(userAuthentication);
        SuapUser user = suapApi.getUserData(suapTokens.getAccess());

        User userExists = this.userRepository.findByRegistry(user.getMatricula());

        if (userExists == null) {
            UserType userType = user.getMatricula().length() == 14 ? UserType.STUDENT : UserType.SERVER;
            User newUser = new User(user.getMatricula(), user.getNome_usual(), userType);

            this.userRepository.save(newUser);
        }

        return jwt.generateToken(user.getMatricula());
    }

    @GetMapping("/user/data")
    @JwtAuthentication
    public User getData(@RequestAttribute("registry") String registry) {
         return userRepository.findByRegistry(registry);
    }

    // @PostMapping("/user")
    // public User saveUser(@RequestBody User user) {
    //     return userRepository.save(user);
    // }

    // @GetMapping("/user")
    // public List<User> listUsers() {
    //     return userRepository.findAll();
    // }

    // @GetMapping("/user/{uuid}")
    // public User listUser(@PathVariable(value = "uuid") UUID uuid) {
    //     return userRepository.findById(uuid).get();
    // }
}
