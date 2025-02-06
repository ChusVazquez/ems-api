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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chusdev.ems.backend.api.models.dto.ClaseDTO;
import com.chusdev.ems.backend.api.models.dto.HorarioDTO;
import com.chusdev.ems.backend.api.models.dto.mapper.ClaseMapper;
import com.chusdev.ems.backend.api.models.dto.mapper.HorarioMapper;
import com.chusdev.ems.backend.api.models.entities.Horario;
import com.chusdev.ems.backend.api.services.ClaseService;
import com.chusdev.ems.backend.api.services.HorarioService;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private HorarioService service;

    @Autowired
    private ClaseService claseService;

    @Autowired
    private HorarioMapper horarioMapper;

    @Autowired
    private ClaseMapper claseMapper;

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

    @GetMapping("/{id}/getClases")
    public List<ClaseDTO> getClases(@PathVariable Long id){
        return claseMapper.listToDTO(claseService.findByHorarioId(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody HorarioDTO horarioDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(horarioDTO));
    }

    /**
     * Genera las N clases siguientes de ese horario de forma automática
     * @param id - Identificador del horario del que queremos generar clases
     * @param num - [Opcional] nº de clases a generar (por defecto 1)
     * @return List<ClaseDTO>
     */
    @PostMapping("/{id}/nextClases")
    public List<ClaseDTO> nextClases(
            @PathVariable Long id,
            @RequestParam(defaultValue = "1") int num)
    {
        return claseMapper.listToDTO(service.nextClases(id, num));
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
