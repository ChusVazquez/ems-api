package com.chusdev.ems.backend.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chusdev.ems.backend.api.models.entities.Grupo;
import com.chusdev.ems.backend.api.services.GrupoService;
import com.chusdev.ems.backend.api.utils.ValidationUtils;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    private static final Logger log = LoggerFactory.getLogger(GrupoController.class);

    @Autowired
    private GrupoService service;
    
    @GetMapping
    public List<Grupo> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Grupo> optGrupo = service.findById(id);
        if (optGrupo.isPresent()){
            return ResponseEntity.ok(optGrupo.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Grupo grupo, BindingResult result){
        if(result.hasErrors()){
            return ValidationUtils.handleValidationErrors(result);
        }
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.save(grupo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid 
                                    @RequestBody Grupo grupo, 
                                    @PathVariable Long id, 
                                    BindingResult result) {
        
        log.info("Update request for grupo ID {} with data: {}", id, grupo);                                        

        if(result.hasErrors()){
            log.warn("Validation errors: {}", result.getAllErrors());
            return ValidationUtils.handleValidationErrors(result);
        }

        Optional<Grupo> optGrupo = service.update(grupo, id);
        if (!optGrupo.isPresent()) {
            log.warn("Grupo with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
        
        log.info("Updating grupo ID {} with new data", id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.update(optGrupo.orElseThrow(), id));                
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<Grupo> optGrupo = service.findById(id);
        if (optGrupo.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }    
}
