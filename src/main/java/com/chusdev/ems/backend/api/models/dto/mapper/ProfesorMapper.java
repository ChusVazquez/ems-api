package com.chusdev.ems.backend.api.models.dto.mapper;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chusdev.ems.backend.api.models.dto.ProfesorDTO;
import com.chusdev.ems.backend.api.models.entities.Grupo;
import com.chusdev.ems.backend.api.models.entities.Profesor;
import com.chusdev.ems.backend.api.repositories.GrupoRepository;

@Component
public class ProfesorMapper {
     @Autowired
    GrupoRepository grupoRepository;

    @Autowired
    ModelMapper modelMapper;

    public Profesor dtoToEntity(ProfesorDTO profesorDTO){
        Profesor profesor = modelMapper.map(profesorDTO, Profesor.class);

        if (profesorDTO.getGrupoId() != null && profesorDTO.getGrupoId() > 0)
        {
            Optional<Grupo> grupoProfesor = grupoRepository.findById(profesorDTO.getGrupoId());
            if (grupoProfesor.isPresent())
            {
                profesor.setGrupo(grupoProfesor.get());
            }
        }

        return profesor;
    }

    public ProfesorDTO entityToDTO(Profesor profesor){
        ProfesorDTO profesorDTO = modelMapper.map(profesor, ProfesorDTO.class);

        Grupo grupoProfesor = profesor.getGrupo();
        if (grupoProfesor != null)
        {
            profesorDTO.setGrupoId(grupoProfesor.getId());
        }

        return profesorDTO;
    }
}
