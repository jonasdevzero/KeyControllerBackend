package com.core.application.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.core.domain.models.Sector;
import com.core.domain.repository.SectorRepository;
import com.core.application.errors.GlobalExceptionHandler;

@RestController
public class SectorController extends GlobalExceptionHandler{
    
    @Autowired
    SectorRepository sectorRepository;

    @PostMapping("/sector")
    @ResponseStatus(HttpStatus.CREATED)
    public Sector save(@RequestBody Sector sector) {
        
        return sectorRepository.save(sector);
    }

    @GetMapping("/sector")
    public List<Sector> list() {
        
        return sectorRepository.findAll();
    }

    @GetMapping("/sector/{id}")
    public Object listUnique(@PathVariable(value = "id") UUID id) {
        
        if (sectorRepository.existsById(id)) {
            return sectorRepository.findById(id).get();
        }
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found");
    }

    @PutMapping("/sector")
    public Object update(@RequestBody Sector sector) {
        
        if (sectorRepository.existsById(sector.getId())) {
            if(sector.getName()!=null){
                Sector update = sectorRepository.findById(sector.getId()).get();

                update.setName(sector.getName());
                sectorRepository.save(update);

                return update;
            } 
            return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inconsistent Data");
        }
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found");
    }

    @DeleteMapping("/sector")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody Sector sector) {
        
        sectorRepository.deleteById(sector.getId());
    }
}
