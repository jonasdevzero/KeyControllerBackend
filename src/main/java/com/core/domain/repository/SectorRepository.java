package com.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.domain.models.Sector;

public interface SectorRepository extends JpaRepository<Sector, Integer> {

}
