package com.core.application.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.core.application.errors.ErrorMessage;
import com.core.domain.entities.Key;
import com.core.domain.repository.KeyRepository;

@RestController
@RequestMapping(value = "/api")
public class KeyController {
    @Autowired
    KeyRepository keyRepository;

    @PostMapping("/key")
    public Key saveKey(@RequestBody Key key){
        return keyRepository.save(key);
    }
    @GetMapping("/key")
    public List<Key> listKeys(){
        return keyRepository.findAll();
    }
    @GetMapping("/key/{id}")
    public Key listKey(@PathVariable (value= "id") UUID id){
        return keyRepository.findById(id).get();
    }

    @PutMapping("/key")
    @ResponseStatus(HttpStatus.OK) //status code
    public Object updateKey(@RequestBody Key key){
        Key updateKey = keyRepository.findById(key.getId()).get();
        
        updateKey.setNumber(key.getNumber());
        updateKey.setSectorId(key.getSectorId());

        keyRepository.save(updateKey);

        return updateKey;
    }

    // @ExceptionHandler()
    // @ResponseStatus(HttpStatus.NOT_FOUND)
    // public Object handleException(){

    //     ErrorMessage errorMessage = new ErrorMessage();
    //     errorMessage.setMessage("Chave não encontrada");
        
    //     return errorMessage;
        
    // }
}
