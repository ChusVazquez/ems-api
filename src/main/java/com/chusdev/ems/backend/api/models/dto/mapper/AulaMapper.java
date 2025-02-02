package com.chusdev.ems.backend.api.models.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chusdev.ems.backend.api.models.dto.AulaDTO;
import com.chusdev.ems.backend.api.models.entities.Aula;

@Component
public class AulaMapper {
    @Autowired
    ModelMapper modelMapper;

    public Aula dtoToEntity(AulaDTO aulaDTO){
        Aula aula = modelMapper.map(aulaDTO, Aula.class);

        return aula;
    }

    public AulaDTO entityToDTO(Aula aula){
        AulaDTO aulaDTO = modelMapper.map(aula, AulaDTO.class);

        return aulaDTO;
    }
}
