package com.core.infra.repository.jpa.user;

import org.springframework.stereotype.Repository;

import com.core.application.dto.user.UserResponseDTO;
import com.core.domain.entities.User;
import com.core.domain.repository.user.CreateUserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CreateUserJpaRepository implements CreateUserRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserResponseDTO create(User user) {
        entityManager.persist(user);
        return new UserResponseDTO(user);
    }
}
