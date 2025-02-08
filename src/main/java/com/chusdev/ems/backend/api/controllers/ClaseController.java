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

import com.chusdev.ems.backend.api.models.dto.AsistenciaDTO;
import com.chusdev.ems.backend.api.models.dto.ClaseDTO;
import com.chusdev.ems.backend.api.models.dto.mapper.AsistenciaMapper;
import com.chusdev.ems.backend.api.models.dto.mapper.ClaseMapper;
import com.chusdev.ems.backend.api.models.entities.Clase;
import com.chusdev.ems.backend.api.services.AsistenciaService;
import com.chusdev.ems.backend.api.services.ClaseService;

@RestController
@RequestMapping("/clases")
public class ClaseController {

    @Autowired
    private ClaseService service;

    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    private AsistenciaMapper asistenciaMapper;

    @Autowired
    private ClaseMapper claseMapper;

    @GetMapping
    public List<ClaseDTO> list(){
        return claseMapper.listToDTO(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Clase> optClase = service.findById(id);
        if (optClase.isPresent()){
            ClaseDTO claseDTO = claseMapper.entityToDTO(optClase.orElseThrow());
            return ResponseEntity.ok(claseDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/getAsistencias")
    public List<AsistenciaDTO> getClases(@PathVariable Long id){
        return asistenciaMapper.listToDTO(asistenciaService.findByClaseId(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClaseDTO claseDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(claseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ClaseDTO claseDTO, @PathVariable Long id){
        Optional<Clase> optClase = service.findById(id);
        if (optClase.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.update(claseDTO, id));
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<Clase> optClase = service.findById(id);
        if (optClase.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
