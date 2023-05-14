package com.core.application.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.application.dto.user.CreateUserDTO;
import com.core.application.dto.user.UserResponseDTO;
import com.core.domain.entities.User;
import com.core.domain.repository.user.CreateUserRepository;
import com.core.infra.repository.jpa.user.CreateUserJpaRepository;

@Service
public class CreateUserService {
    @Autowired
    private final CreateUserRepository createUserRepository;

    public CreateUserService(CreateUserJpaRepository createUserRepository) {
        this.createUserRepository = createUserRepository;
    }

    public UserResponseDTO execute(CreateUserDTO data) {
        User user = new User(data);
        return this.createUserRepository.create(user);
    }

}
