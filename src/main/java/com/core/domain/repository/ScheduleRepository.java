package com.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.domain.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    
}
