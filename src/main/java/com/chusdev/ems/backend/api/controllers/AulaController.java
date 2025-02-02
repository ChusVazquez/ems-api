package com.chusdev.ems.backend.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chusdev.ems.backend.api.models.dto.AulaDTO;
import com.chusdev.ems.backend.api.models.entities.Aula;
import com.chusdev.ems.backend.api.services.AulaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/aulas")
public class AulaController {


    @Autowired
    private AulaService service;

    @GetMapping
    public List<Aula> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Aula> optAula = service.findById(id);
        if (optAula.isPresent()){
            return ResponseEntity.ok(optAula.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AulaDTO aulaDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(aulaDTO));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody AulaDTO aulaDTO, @PathVariable Long id){
        Optional<Aula> optAula = service.findById(id);
        if (optAula.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.update(aulaDTO, id));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<Aula> optAula = service.findById(id);

        if (optAula.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
