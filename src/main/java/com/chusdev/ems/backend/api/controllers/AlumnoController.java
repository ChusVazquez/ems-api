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

import com.chusdev.ems.backend.api.models.dto.AlumnoDTO;
import com.chusdev.ems.backend.api.models.entities.Alumno;
import com.chusdev.ems.backend.api.services.AlumnoService;
import com.chusdev.ems.backend.api.utils.ValidationUtils;

import jakarta.validation.Valid;

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
    public ResponseEntity<?> create(@Valid @RequestBody AlumnoDTO alumnoDTO, BindingResult result){        
        if(result.hasErrors()){
            return ValidationUtils.handleValidationErrors(result);
        }

        if (alumnoDTO != null && alumnoDTO.getId() == 0){
            alumnoDTO.setId(null);
        }

        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.save(alumnoDTO));
    }

     @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid
                                    @RequestBody AlumnoDTO alumnoDTO,
                                    BindingResult result,                                     
                                    @PathVariable Long id) {
        if(result.hasErrors()){
            return ValidationUtils.handleValidationErrors(result);
        }
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
