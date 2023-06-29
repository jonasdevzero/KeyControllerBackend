package com.core.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.core.domain.models.Key;
import com.core.domain.models.Sector;
import com.core.domain.repository.KeyRepository;
import com.core.domain.repository.SectorRepository;
import com.core.application.errors.GlobalExceptionHandler;
import com.core.infra.security.JWT;


@RestController
public class KeyController extends GlobalExceptionHandler {

    @Autowired
    KeyRepository keyRepository;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    JWT jwtToken;

    @PostMapping("/key")
    // @JwtAuthentication
    // @EnsureUserType(UserType.SERVER)
    @ResponseStatus(HttpStatus.CREATED)
    public Object save(@RequestBody Key key) {

        Integer sectorId = key.getSector().getId();
        String keyNumber = key.getNumber();
       
        if(sectorId != null && keyNumber != null){
            if(sectorRepository.existsById(sectorId)){
                
                Sector sector = sectorRepository.findById(sectorId).get();
                Key dataKey = new Key(sector, keyNumber);
                // dataKey.setSector(sector);
                return keyRepository.save(dataKey);
            }
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found");
        }
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inconsistent Data");
    }

    @GetMapping("/key")
    public List<Key> list() {

        return keyRepository.findAll();
    }

    @GetMapping("/key/{id}")
    public Object listUnique(@PathVariable(value = "id") Integer id) {

        if (keyRepository.existsById(id)) {
            return keyRepository.findById(id).get();
        }
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found");
    }

    @PutMapping("/key")
    public Object update(@RequestBody Key key) {

        Integer sectorId = key.getSector().getId();
        String keyNumber = key.getNumber();        
        Integer keyId = key.getId();

       
        if (keyRepository.existsById(keyId)) {

            if (keyNumber != null || key.getSector() != null || sectorRepository.existsById(sectorId)   ) {

                Key update = keyRepository.findById(key.getId()).get();
                Sector sector = sectorRepository.findById(sectorId).get();
                
                update.setNumber(key.getNumber() != null ? key.getNumber() : update.getNumber());
                update.setSector(sector);
                
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
