package com.core.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.domain.entities.Sector;

public interface SectorRepository extends JpaRepository<Sector, UUID> {
    
}