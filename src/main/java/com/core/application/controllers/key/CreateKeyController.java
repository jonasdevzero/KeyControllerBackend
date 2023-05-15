package com.core.application.controllers.key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.domain.entities.Key;
import com.core.domain.repository.KeyRepository;

@RestController
@RequestMapping(value = "/api")
public class CreateKeyController {
    @Autowired
    KeyRepository keyRepository;

    @PostMapping("/key")
    public Key saveKey(@RequestBody Key key){
        return keyRepository.save(key);
    }
}
