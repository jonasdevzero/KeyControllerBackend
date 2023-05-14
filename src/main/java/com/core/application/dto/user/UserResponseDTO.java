package com.core.application.dto.user;

import java.util.UUID;

import com.core.domain.entities.User;

public record UserResponseDTO(UUID id, String name, String type) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getName(), user.getType());
    }
}