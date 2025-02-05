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

import com.chusdev.ems.backend.api.models.dto.HorarioDTO;
import com.chusdev.ems.backend.api.models.dto.mapper.HorarioMapper;
import com.chusdev.ems.backend.api.models.entities.Horario;
import com.chusdev.ems.backend.api.services.HorarioService;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private HorarioService service;

    @Autowired
    private HorarioMapper horarioMapper;

    @GetMapping
    public List<HorarioDTO> list(){
        return horarioMapper.listToDTO(service.findAll());    
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Horario> optHorario = service.findById(id);
        if (optHorario.isPresent()){
            HorarioDTO horarioDTO = horarioMapper.entityToDTO(optHorario.orElseThrow());
            return ResponseEntity.ok(horarioDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody HorarioDTO horarioDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(horarioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody HorarioDTO horarioDTO, @PathVariable Long id){
        Optional<Horario> optHorario = service.findById(id);
        if (optHorario.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.update(horarioDTO, id));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<Horario> optHorario = service.findById(id);
        if (optHorario.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
