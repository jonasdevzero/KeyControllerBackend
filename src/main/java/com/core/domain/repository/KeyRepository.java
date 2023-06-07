package com.core.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.core.domain.entities.Key;

public interface KeyRepository extends JpaRepository<Key, UUID>{
}
