package com.core.application.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.core.infra.security.JWT;

import io.jsonwebtoken.Jwts;

import com.core.application.errors.GlobalExceptionHandler;
import com.core.domain.dto.approveKey.ApproveKey;
import com.core.domain.models.Key;
import com.core.domain.models.Schedule;
import com.core.domain.models.User;
import com.core.domain.repository.KeyRepository;
import com.core.domain.repository.ScheduleRepository;
import com.core.domain.repository.UserRepository;

@RestController
public class ScheduleController extends GlobalExceptionHandler {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    KeyRepository keyRepository;

    @GetMapping("/key/register/")
    public List<Schedule> list() {

        return scheduleRepository.findAll();
    }

    @GetMapping("/key/register/{id}")
    public Object listUnique(@PathVariable(value = "id") Integer id) {

        if (scheduleRepository.existsById(id)) {
            return scheduleRepository.findById(id);
        }
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found");
    }

    @PostMapping("/key/register/")
    @ResponseStatus(HttpStatus.CREATED)
    public Object save(@RequestBody Schedule schedule) {

        boolean boo = userRepository.existsById(schedule.getUser().getRegistry()) &&
                keyRepository.existsById(schedule.getKey().getId()) &&
                (schedule.getAcquisitionDate() != null) &&
                (schedule.getDevolutionDate() != null);

        LocalDateTime acquisition = schedule.getAcquisitionDate();
        LocalDateTime devolution = schedule.getDevolutionDate();
        Key key = schedule.getKey();
        User user = schedule.getUser();

        if (boo) {

            Schedule data = new Schedule(false, acquisition, devolution, key, user);
            return scheduleRepository.save(data);
        }
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found");

    }

}
