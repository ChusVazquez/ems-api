package com.chusdev.ems.backend.api.models.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chusdev.ems.backend.api.models.dto.GrupoDTO;
import com.chusdev.ems.backend.api.models.entities.Grupo;

@Component
public class GrupoMapper {
    @Autowired
    ModelMapper modelMapper;

    public Grupo dtoToEntity(GrupoDTO GrupoDTO){
        return modelMapper.map((GrupoDTO), Grupo.class);
    }

    public GrupoDTO entityToDTO(Grupo grupo){
        return modelMapper.map(grupo, GrupoDTO.class);
    }

}
