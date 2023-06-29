package com.core.application.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.core.application.errors.GlobalExceptionHandler;
import com.core.domain.dto.approveKey.ApproveKey;
import com.core.domain.models.Key;
import com.core.domain.models.Schedule;
import com.core.domain.models.User;
import com.core.domain.models.UserType;
import com.core.domain.repository.KeyRepository;
import com.core.domain.repository.ScheduleRepository;
import com.core.domain.repository.UserRepository;
import com.core.infra.security.annotations.EnsureUserType;
import com.core.infra.security.annotations.JwtAuthentication;

@RestController
public class ScheduleController extends GlobalExceptionHandler {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    KeyRepository keyRepository;


    @GetMapping("/key/register")
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

    // @JwtAuthentication
    // @EnsureUserType(UserType.SERVER)
    @PostMapping("/key/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Object save(@RequestBody Schedule schedule) {

        
        LocalDateTime acquisition = schedule.getAcquisitionDate();
        LocalDateTime devolution = schedule.getDevolutionDate();
        String registryUser = schedule.getUser().getRegistry();
        Integer idKey = schedule.getKey().getId();

        if(userRepository.existsById(registryUser) && keyRepository.existsById(idKey)){
            
            User user = userRepository.findById(registryUser).get();
            Key key = keyRepository.findById(idKey).get();
            if (schedule.getAcquisitionDate() != null && schedule.getDevolutionDate() != null) {
    
                Schedule data = new Schedule(null, acquisition, devolution, key, user);
                return scheduleRepository.save(data);
            }
        }

        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found");

    }

    @JwtAuthentication
    // @EnsureUserType(UserType.SERVER)
    @PostMapping("/key/register/approve")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void approve(@RequestBody ApproveKey approveKey) {
        
        Integer id = approveKey.getScheduleId();
        if (approveKey.getApprove()) {
            if (scheduleRepository.existsById(id)) {
                Schedule data = scheduleRepository.findById(id).get();
                data.setCaught(false);

                scheduleRepository.save(data);
             }
        } else if (!approveKey.getApprove()) {
           scheduleRepository.deleteById(id);
        }
    }

}
