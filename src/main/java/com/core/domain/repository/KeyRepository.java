package com.core.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.core.domain.models.Key;

import jakarta.transaction.Transactional;

public interface KeyRepository extends JpaRepository<Key, Integer>{
}
