package com.core.application.controllers;

import java.util.List;
import java.util.UUID;
import java.util.logging.ErrorManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.core.application.errors.GlobalExceptionHandler;
import com.core.domain.entities.Key;
import com.core.domain.repository.KeyRepository;


@RestController
@RequestMapping(value = "/api")
public class KeyController extends GlobalExceptionHandler {
    @Autowired
    KeyRepository keyRepository;

    @PostMapping("/key")
    public Key save(@RequestBody Key key) {
        return keyRepository.save(key);
    }

    @GetMapping("/key")
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

            keyRepository.save(key);

            return key;
        }
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found");
        
    }

    @DeleteMapping("/key")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody Key key) {
        keyRepository.deleteById(key.getId());


    }

    // @ResponseStatus(HttpStatus.OK)
    // @ExceptionHandler(NoSuchElementException.class)
    // public String ppp(NoSuchElementException e){

    // return "ttt";

    // }

}
