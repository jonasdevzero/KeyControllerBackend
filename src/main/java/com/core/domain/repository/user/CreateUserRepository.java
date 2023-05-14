package com.core.domain.repository.user;

import com.core.application.dto.user.UserResponseDTO;
import com.core.domain.entities.User;

public interface CreateUserRepository {
    UserResponseDTO create(User user);
}
