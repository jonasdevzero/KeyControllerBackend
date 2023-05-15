package com.core.application.controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.domain.entities.Key;
import com.core.domain.repository.KeyRepository;

@RestController
@RequestMapping(value = "/api")
public class KeyController {
    @Autowired
    KeyRepository keyRepository;
    // GET METHODS
    @GetMapping("/key")
    public List<Key> listKeys(){
        return keyRepository.findAll();
    }
    @GetMapping("/key/{uuid}")
    public Key listKey(@PathVariable (value= "uuid") UUID uuid){
        return keyRepository.findById(uuid).get();
    }
    // POST METHODS
    @PostMapping("/key")
    public Key saveKey(@RequestBody Key key){
        return keyRepository.save(key);
    }
}
