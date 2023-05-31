package com.core.application.controllers;

import java.util.List;
import java.util.UUID;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
    public Object updateKey(@RequestBody Key key) throws NotFoundException{
        Key updateKey = keyRepository.findById(key.getId()).get();
        
        updateKey.setNumber(key.getNumber());
        updateKey.setSectorId(key.getSectorId());

        keyRepository.save(updateKey);

        return updateKey;   
    }
    // @ResponseStatus(HttpStatus.OK)
    // @ExceptionHandler(NoSuchElementException.class)
    // public String ppp(NoSuchElementException e){
        
    //     return "ttt";
        
    // }
    
}
