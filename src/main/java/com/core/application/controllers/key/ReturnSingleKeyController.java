package com.core.application.controllers.key;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.domain.entities.Key;
import com.core.domain.repository.KeyRepository;

@RestController
@RequestMapping(value = "/api")
public class ReturnSingleKeyController {
    @Autowired
    KeyRepository keyRepository;
    
    @GetMapping("/key/{uuid}")
    public Key listKey(@PathVariable (value= "uuid") UUID uuid){
        return keyRepository.findById(uuid).get();
    }
}
