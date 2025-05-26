package com.chusdev.ems.backend.api.models.dto.mapper;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chusdev.ems.backend.api.models.dto.GrupoDTO;
import com.chusdev.ems.backend.api.models.entities.Grupo;
import com.chusdev.ems.backend.api.repositories.GrupoRepository;

@Component
public class GrupoMapper {
    @Autowired
    ModelMapper modelMapper;

    @Autowired 
    GrupoRepository grupoRepository;

    public Grupo dtoToEntity(GrupoDTO GrupoDTO){
        return modelMapper.map((GrupoDTO), Grupo.class);
    }

    public Grupo dtoIdToEntity(GrupoDTO GrupoDTO){
        Grupo grupo = null;

        if(GrupoDTO != null 
        && GrupoDTO.getId() > 0){
            Optional<Grupo> optGrupo = grupoRepository
                .findById(GrupoDTO.getId());
            if (optGrupo.isPresent()){
                grupo = optGrupo.orElseThrow();
            }
        }

        return grupo;
    }

    public GrupoDTO entityToDTO(Grupo grupo){
        return modelMapper.map(grupo, GrupoDTO.class);
    }

}
