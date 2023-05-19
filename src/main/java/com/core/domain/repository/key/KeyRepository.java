package com.core.domain.repository.key;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.domain.entities.key.Key;

public interface KeyRepository extends JpaRepository<Key, UUID>{
}
