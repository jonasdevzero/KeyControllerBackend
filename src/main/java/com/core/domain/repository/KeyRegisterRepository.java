package com.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.domain.models.KeyRegister;

public interface KeyRegisterRepository extends JpaRepository<KeyRegister, Integer> {
 
}
