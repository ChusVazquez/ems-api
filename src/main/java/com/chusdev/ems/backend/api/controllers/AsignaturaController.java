package com.chusdev.ems.backend.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chusdev.ems.backend.api.models.dto.AsignaturaDTO;
import com.chusdev.ems.backend.api.models.entities.Asignatura;
import com.chusdev.ems.backend.api.services.AsignaturaService;

@RestController
@RequestMapping("/asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaService service;

    @GetMapping
    public List<Asignatura> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Asignatura> optAsignatura = service.findById(id);
        if (optAsignatura.isPresent()){
            return ResponseEntity.ok(optAsignatura.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AsignaturaDTO asignaturaDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(asignaturaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody AsignaturaDTO asignaturaDTO, @PathVariable Long id){
        Optional<Asignatura> optAsignatura = service.findById(id);
        if (optAsignatura.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.update(asignaturaDTO, id));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<Asignatura> optAsignatura = service.findById(id);
        if (optAsignatura.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
