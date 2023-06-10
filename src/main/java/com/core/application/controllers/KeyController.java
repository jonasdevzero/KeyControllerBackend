package com.core.application.controllers;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.core.domain.models.Key;
import com.core.domain.repository.KeyRepository;
import com.core.errors.GlobalExceptionHandler;
import com.core.services.security.JWTToken;


@RestController
public class KeyController extends GlobalExceptionHandler {

    @Autowired
    KeyRepository keyRepository;

    @Autowired
    JWTToken jwtToken;

    @PostMapping("/key")
    @ResponseStatus(HttpStatus.CREATED)
    public Key save(@RequestBody Key key) {

        return keyRepository.save(key);
    }

    @GetMapping("/key")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Key> list(@RequestHeader(name = "Authorization") String tokenEncoded) {
        
        jwtToken.authorize(tokenEncoded);
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
