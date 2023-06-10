package com.core.application.controllers;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.core.domain.repository.UserRepository;
import com.core.errors.GlobalExceptionHandler;
import com.core.services.SuapAPI;
import com.core.services.dto.JWTObject;
import com.core.services.dto.TokenObject;
import com.core.services.dto.UserAuthentication;
import com.core.services.dto.userData.UserData;
import com.core.services.security.JWTGenerator;

@RestController
public class UserController extends GlobalExceptionHandler{

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    JWTGenerator jwtGenera;

    @Autowired
    SuapAPI requestToken;
    
    @PostMapping("/authentication/token/")
    public TokenObject returnToken(@RequestBody UserAuthentication userAuthentication) {
        return requestToken.authentication(userAuthentication);
    }
    @PostMapping("/create-token/")
    public JWTObject returnMyData(@RequestBody UserAuthentication userAuthentication) throws RestClientException, URISyntaxException {
        UserData data = requestToken.returnData(requestToken.authentication(userAuthentication).getAccess());
        return jwtGenera.generateToken(data.getMatricula());
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
