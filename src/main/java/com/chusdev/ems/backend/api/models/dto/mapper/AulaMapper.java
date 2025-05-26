package com.chusdev.ems.backend.api.models.dto.mapper;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chusdev.ems.backend.api.models.dto.AulaDTO;
import com.chusdev.ems.backend.api.models.entities.Aula;
import com.chusdev.ems.backend.api.repositories.AulaRepository;

@Component
public class AulaMapper {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AulaRepository aulaRepository;

    public Aula dtoToEntity(AulaDTO aulaDTO){
        Aula aula = modelMapper.map(aulaDTO, Aula.class);

        return aula;
    }

    public Aula dtoIdToEntity(AulaDTO aulaDTO){
        Aula aula = null;

        if(aulaDTO != null 
        && aulaDTO.getId() > 0){
            Optional<Aula> optAula = aulaRepository
                .findById(aulaDTO.getId());
            if (optAula.isPresent()){
                aula = optAula.orElseThrow();
            }
        }

        return aula;
    }

    public AulaDTO entityToDTO(Aula aula){
        AulaDTO aulaDTO = modelMapper.map(aula, AulaDTO.class);

        return aulaDTO;
    }
}
