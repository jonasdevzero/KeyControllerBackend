package com.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.domain.models.Key;

public interface KeyRepository extends JpaRepository<Key, Integer>{
}
