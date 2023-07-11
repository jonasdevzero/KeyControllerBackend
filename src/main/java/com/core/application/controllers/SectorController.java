package com.core.application.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.core.domain.models.UserType;
import com.core.domain.repository.SectorRepository;
import com.core.infra.security.annotations.EnsureUserType;
import com.core.infra.security.annotations.JwtAuthentication;
import com.core.application.errors.GlobalExceptionHandler;

@RestController
public class SectorController extends GlobalExceptionHandler{

    @Autowired
    SectorRepository sectorRepository;

    @JwtAuthentication
    @EnsureUserType(UserType.SERVER)
    @PostMapping("/sector")
    @ResponseStatus(HttpStatus.CREATED)
    public Object save(@RequestBody Sector sector) {

        if( sector.getName() != null ){
            return sectorRepository.save(sector);
        }
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inconsistent Data");
    }

    @GetMapping("/sector")
    public List<Sector> list() {

        return sectorRepository.findAll();
    }

    @GetMapping("/sector/{id}")
    public Object listUnique(@PathVariable(value = "id") Integer id) {

        if (sectorRepository.existsById(id)) {
            return sectorRepository.findById(id).get();
        }
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found");
    }

    @JwtAuthentication
    @EnsureUserType(UserType.SERVER)
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

    @JwtAuthentication
    @EnsureUserType(UserType.SERVER)
    @DeleteMapping("/sector")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody Sector sector, HttpServletResponse response) throws IOException{
        if(sectorRepository.existsById(sector.getId())){
            Sector sectorData = sectorRepository.findById(sector.getId()).get();
            sectorData.setDeleteAt(LocalDateTime.now());
            sectorRepository.save(sectorData);
        }else{
            response.sendError(HttpStatus.NOT_FOUND.value(), "Data Not Found");
        }
    }
}
