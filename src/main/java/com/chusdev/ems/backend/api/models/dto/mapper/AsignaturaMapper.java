package com.chusdev.ems.backend.api.models.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chusdev.ems.backend.api.models.dto.AsignaturaDTO;
import com.chusdev.ems.backend.api.models.dto.EstudioDTO;
import com.chusdev.ems.backend.api.models.entities.Asignatura;
import com.chusdev.ems.backend.api.models.entities.Estudio;

@Component
public class AsignaturaMapper {
    @Autowired
    ModelMapper modelMapper;

    public Asignatura dtoToEntity(AsignaturaDTO asignaturaDTO){
        Asignatura asignatura = modelMapper.map(asignaturaDTO, Asignatura.class);
        asignatura.setEstudio(modelMapper.map(asignaturaDTO.getEstudio(), Estudio.class));
        
        return asignatura;
    }

    public AsignaturaDTO entityToDTO(Asignatura asignatura){
        AsignaturaDTO asignaturaDTO = modelMapper.map(asignatura, AsignaturaDTO.class);
        asignaturaDTO.setEstudio(modelMapper.map(asignatura.getEstudio(), EstudioDTO.class));

        return asignaturaDTO;
    }
}
