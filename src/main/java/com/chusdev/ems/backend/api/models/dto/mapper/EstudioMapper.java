package com.chusdev.ems.backend.api.models.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chusdev.ems.backend.api.models.dto.EstudioDTO;
import com.chusdev.ems.backend.api.models.entities.Estudio;

@Component
public class EstudioMapper {
    @Autowired
    ModelMapper modelMapper;

    public Estudio dtoToEntity(EstudioDTO estudioDTO){
        Estudio estudio = modelMapper.map(estudioDTO, Estudio.class);

        return estudio;
    }

    public EstudioDTO entityToDTO(Estudio estudio){
        EstudioDTO estudioDTO = modelMapper.map(estudio, EstudioDTO.class);

        return estudioDTO;
    }
}
