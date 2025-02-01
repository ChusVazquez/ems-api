package com.chusdev.ems.backend.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chusdev.ems.backend.api.models.dto.ProfesorDTO;
import com.chusdev.ems.backend.api.models.entities.Profesor;
import com.chusdev.ems.backend.api.services.ProfesorService;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {
    
    @Autowired
    private ProfesorService service;

    @GetMapping
    public List<Profesor> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Profesor> optProfesor = service.findById(id);
        if (optProfesor.isPresent()){
            return ResponseEntity.ok(optProfesor.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProfesorDTO profesorDTO){        
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.save(profesorDTO));
    }

     @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ProfesorDTO profesorDTO, @PathVariable Long id) {
        Optional<Profesor> optProfesor = service.findById(id);
        if (optProfesor.isPresent()){            
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.update(profesorDTO, id));
        }
        return ResponseEntity.notFound().build();
    }
}
