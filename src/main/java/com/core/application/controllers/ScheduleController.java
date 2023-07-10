package com.core.application.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.core.domain.models.KeyRegister;
import com.core.domain.models.Schedule;
import com.core.domain.models.User;
import com.core.domain.models.UserType;
import com.core.domain.repository.KeyRegisterRepository;
import com.core.domain.repository.KeyRepository;
import com.core.domain.repository.ScheduleRepository;
import com.core.domain.repository.UserRepository;
import com.core.infra.security.annotations.EnsureUserType;
import com.core.infra.security.annotations.JwtAuthentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ScheduleController extends GlobalExceptionHandler {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    KeyRegisterRepository keyRegisterRepository;

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

    @JwtAuthentication
    // @EnsureUserType(UserType.SERVER)
    @PostMapping("/key/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Object save(@RequestBody Schedule schedule, HttpServletRequest request) {

        LocalDateTime acquisition = schedule.getAcquisitionDate();
        LocalDateTime devolution = schedule.getDevolutionDate();
        String registryUser = schedule.getUser().getRegistry();
        Integer idKey = schedule.getKey().getId();
        String registry = request.getAttribute("registry").toString();

        User user = userRepository.findByRegistry(registryUser);
        Key key = keyRepository.findById(idKey).get();

        User currentUser = userRepository.findByRegistry(registry);

        LocalDateTime ss = null;
        boolean dateNotInUse = false;


        // verificar se as datas já estão sendo utilizadas
        List<Schedule> listSchedules = scheduleRepository.findByKey(key);
        for (Schedule item : listSchedules) {
            dateNotInUse = (item.getDevolutionDate().compareTo(devolution) == item.getAcquisitionDate().compareTo(acquisition)
                    && item.getDevolutionDate().compareTo(devolution) > item.getAcquisitionDate().compareTo(acquisition)
                    && item.getDevolutionDate().compareTo(devolution) != 0
                    && item.getAcquisitionDate().compareTo(acquisition) != 0) ? true : false;
        }
        // return dateNotInUse.toString();
        if(dateNotInUse){
            if (acquisition != null && devolution != null) {

                Schedule data = new Schedule(null, acquisition, devolution, key, user);
                if (currentUser.getType() == UserType.SERVER) {

                    boolean inUse = false;
                    // verifica se existe alguma chave que está sendo utilizada
                    List<KeyRegister> listKeyRegisters = keyRegisterRepository.findAll();
                    for (KeyRegister item : listKeyRegisters) {
                        inUse = (!item.getReturned() && item.getKey().getNumber() == key.getNumber()) ? true: false;
                    }
                    if (schedule.getCaught()!=null && schedule.getCaught() && !inUse) {

                        data.setCaught(schedule.getCaught());
                        KeyRegister keyRegister = new KeyRegister(false, acquisition, devolution, user, key);
                        keyRegisterRepository.save(keyRegister);
                    } else {
                        data.setCaught(false);
                    }
                } return scheduleRepository.save(data);
            } return new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found");
        } return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date Is Being Used");

    }

    @JwtAuthentication
    @EnsureUserType(UserType.SERVER)
    @PostMapping("/key/register/approve")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void approve(@RequestBody ApproveKey approveKey) {

        Integer id = approveKey.getScheduleId();
        if (scheduleRepository.existsById(id)) {
            if (approveKey.getApprove()) {

                Schedule data = scheduleRepository.findById(id).get();
                data.setCaught(false);
                scheduleRepository.save(data);
            } else if (!approveKey.getApprove()) {

                scheduleRepository.deleteById(id);
            }
        }
    }

    @JwtAuthentication
    @EnsureUserType(UserType.SERVER)
    @PostMapping("/key/register/{id}/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmSchedule(@PathVariable(value = "id") Integer id, HttpServletResponse response)
            throws IOException {

        if (scheduleRepository.existsById(id)) {

            Schedule schedule = scheduleRepository.findById(id).get();
            schedule.setCaught(true);

            LocalDateTime acquisition = schedule.getAcquisitionDate();
            LocalDateTime devolution = schedule.getDevolutionDate();
            Integer idKey = schedule.getKey().getId();
            User user = schedule.getUser();
            Key key = schedule.getKey();

            scheduleRepository.save(schedule);

            KeyRegister keyRegister = new KeyRegister(idKey, false, acquisition, devolution, acquisition, devolution,
                    key, user);

            keyRegisterRepository.save(keyRegister);

        } else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "Data Not Found");
        }
    }

    @JwtAuthentication
    @DeleteMapping("/key/register/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable(value = "id") Integer id, HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String registry = request.getAttribute("registry").toString();
        if (scheduleRepository.existsById(id)) {

            Schedule data = scheduleRepository.findById(id).get();
            if (data.getUser().getRegistry().equals(registry) || data.getUser().getType() == UserType.SERVER) {

                scheduleRepository.deleteById(id);
            } else {

                response.sendError(HttpStatus.UNAUTHORIZED.value(), "User Unauthorized");
            }
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "Data Not Found");
        }
    }

}
