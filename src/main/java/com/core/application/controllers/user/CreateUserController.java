package com.core.application.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.application.dto.user.CreateUserDTO;
import com.core.application.dto.user.UserResponseDTO;
import com.core.application.services.user.CreateUserService;

@RestController
@RequestMapping("user")
public class CreateUserController {
    @Autowired
    private CreateUserService service;

    @PostMapping
    public UserResponseDTO handle(@RequestBody CreateUserDTO data) {
        return this.service.execute(data);
    }
}
