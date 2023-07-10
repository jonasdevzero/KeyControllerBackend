package com.core.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.domain.models.Key;
import com.core.domain.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    List<Schedule> findByKey(Key key);
}
