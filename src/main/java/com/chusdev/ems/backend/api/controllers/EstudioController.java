package com.chusdev.ems.backend.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chusdev.ems.backend.api.models.dto.EstudioDTO;
import com.chusdev.ems.backend.api.models.entities.Estudio;
import com.chusdev.ems.backend.api.services.EstudioService;

@RestController
@RequestMapping("/estudios")
public class EstudioController {

    @Autowired
    private EstudioService service;

    @GetMapping
    public List<Estudio> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Estudio> optEstudio = service.findById(id);
        if (optEstudio.isPresent()){
            return ResponseEntity.ok(optEstudio.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody EstudioDTO estudioDTO, @PathVariable Long id){
        Optional<Estudio> optEstudio = service.findById(id);
        if (optEstudio.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.update(estudioDTO, id));            
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<Estudio> optEstudio = service.findById(id);

        if (optEstudio.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
