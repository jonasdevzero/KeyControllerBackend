package com.core.domain.repository.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.domain.entities.user.User;

public interface UserRepository extends JpaRepository<User, UUID>{
}
