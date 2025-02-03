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

import com.chusdev.ems.backend.api.models.dto.AlumnoDTO;
import com.chusdev.ems.backend.api.models.entities.Alumno;
import com.chusdev.ems.backend.api.services.AlumnoService;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    
    @Autowired
    private AlumnoService service;

    @GetMapping
    public List<Alumno> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Alumno> optAlumno = service.findById(id);
        if (optAlumno.isPresent()){
            return ResponseEntity.ok(optAlumno.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AlumnoDTO alumnoDTO){        
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.save(alumnoDTO));
    }

     @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody AlumnoDTO alumnoDTO, @PathVariable Long id) {
        Optional<Alumno> optAlumno = service.findById(id);
        if (optAlumno.isPresent()){            
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.update(alumnoDTO, id));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<Alumno> optAlumno = service.findById(id);
        if (optAlumno.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();            
        }

        return ResponseEntity.notFound().build();
    }
}
