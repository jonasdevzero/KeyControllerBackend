package com.core.application.controllers.key;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.domain.entities.key.Key;
import com.core.domain.repository.key.KeyRepository;

@RestController
@RequestMapping(value = "/api")
public class ReturnAllKeysController {
    @Autowired
    KeyRepository keyRepository;
    // GET METHODS
    @GetMapping("/key")
    public List<Key> listKeys(){
        return keyRepository.findAll();
    }
}
