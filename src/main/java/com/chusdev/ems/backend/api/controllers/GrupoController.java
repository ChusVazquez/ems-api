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

import com.chusdev.ems.backend.api.models.entities.Grupo;
import com.chusdev.ems.backend.api.services.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
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
    public ResponseEntity<?> create(@RequestBody Grupo grupo){
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.save(grupo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Grupo grupo, @PathVariable Long id) {
        Optional<Grupo> optGrupo = service.update(grupo, id);
        if (optGrupo.isPresent()){            
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.update(optGrupo.orElseThrow(), id));
        }
        return ResponseEntity.notFound().build();
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
