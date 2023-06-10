package com.core.application.controllers;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.core.domain.models.UserType;
import com.core.infra.security.annotations.EnsureUserType;
import com.core.infra.security.annotations.JwtAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.core.domain.models.Key;
import com.core.domain.repository.KeyRepository;
import com.core.application.errors.GlobalExceptionHandler;
import com.core.infra.security.JWT;


@RestController
public class KeyController extends GlobalExceptionHandler {

    @Autowired
    KeyRepository keyRepository;

    @Autowired
    JWT jwtToken;

    @PostMapping("/key")
    @JwtAuthentication
    @EnsureUserType(UserType.SERVER)
    @ResponseStatus(HttpStatus.CREATED)
    public Key save(@RequestBody Key key) {
        return keyRepository.save(key);
    }


    @GetMapping("/key")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Key> list() {
        return keyRepository.findAll();
    }

    @GetMapping("/key/{id}")
    public Object listUnique(@PathVariable(value = "id") UUID id) {

        if (keyRepository.existsById(id)) {
            return keyRepository.findById(id).get();
        }
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found");
    }

    @PutMapping("/key")
    public Object update(@RequestBody Key key) {

        if (keyRepository.existsById(key.getId())) {
            if (key.getNumber() != null || key.getSectorId() != null) {
                Key update = keyRepository.findById(key.getId()).get();
                update.setNumber(key.getNumber() != null ? key.getNumber() : update.getNumber());
                update.setSectorId(key.getSectorId() != null ? key.getSectorId() : update.getSectorId());

                keyRepository.save(update);

                return update;
            }
            return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inconsistent Data");
        }
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found");
    }

    @DeleteMapping("/key")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody Key key) {

        keyRepository.deleteById(key.getId());
    }

}
