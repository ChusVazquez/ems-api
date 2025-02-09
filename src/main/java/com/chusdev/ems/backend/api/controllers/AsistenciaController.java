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
import com.chusdev.ems.backend.api.models.dto.mapper.AsistenciaMapper;
import com.chusdev.ems.backend.api.models.entities.Asistencia;
import com.chusdev.ems.backend.api.services.AsistenciaService;

@RestController
@RequestMapping("/asistencias")
public class AsistenciaController {
    @Autowired
    private AsistenciaService service;

    @Autowired
    private AsistenciaMapper asistenciaMapper;

    @GetMapping
    public List<AsistenciaDTO> list(){
        return asistenciaMapper.listToDTO(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Asistencia> optAsistencia = service.findById(id);
        if (optAsistencia.isPresent()){
            AsistenciaDTO asistenciaDTO = asistenciaMapper.entityToDto(optAsistencia.orElseThrow());
            return ResponseEntity.ok(asistenciaDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AsistenciaDTO asistenciaDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(asistenciaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody AsistenciaDTO asistenciaDTO, @PathVariable Long id){
        Optional<Asistencia> optAsistencia = service.findById(id);
        if (optAsistencia.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.update(asistenciaDTO, id));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/setEstado/{id}")
    public byte setEstado(@PathVariable Long id, @RequestBody AsistenciaDTO asistenciaDTO) {
        byte estado = 0;
        if (asistenciaDTO != null && asistenciaDTO.getEstado() != null){
            Optional<Asistencia> optAsistencia = service.setEstado(id, asistenciaDTO.getEstado());
            if (optAsistencia.isPresent()){
                estado = optAsistencia.orElseThrow().getEstado();
            }
        }
        
        return estado;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<Asistencia> optAsistencia = service.findById(id);
        if (optAsistencia.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
