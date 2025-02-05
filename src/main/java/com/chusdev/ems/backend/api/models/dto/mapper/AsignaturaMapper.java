package com.chusdev.ems.backend.api.models.dto.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chusdev.ems.backend.api.models.dto.AsignaturaBaseDTO;
import com.chusdev.ems.backend.api.models.dto.AsignaturaDTO;
import com.chusdev.ems.backend.api.models.entities.Asignatura;
import com.chusdev.ems.backend.api.models.entities.Estudio;
import com.chusdev.ems.backend.api.repositories.AsignaturaRepository;
import com.chusdev.ems.backend.api.repositories.EstudioRepository;

@Component
public class AsignaturaMapper {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Autowired
    EstudioRepository estudioRepository;

    public Asignatura dtoToEntity(AsignaturaDTO asignaturaDTO){        
        Asignatura asignatura = modelMapper.map(asignaturaDTO, Asignatura.class);
        Estudio estudio = null;
        if (asignaturaDTO.getEstudio_id() != null && asignaturaDTO.getEstudio_id() > 0){
            Optional<Estudio> optEstudio = estudioRepository.findById(asignaturaDTO.getEstudio_id());
            if (optEstudio.isPresent()){
                estudio = optEstudio.orElseThrow();
            }
        }
        
        asignatura.setEstudio(estudio);
        
        return asignatura;
    }

    /**
     * Se carga Asignatura únicamente en base al Id del DTO
     * 
     * @param asignaturaBaseDTO
     * @return Asignatura
     */
    public Asignatura baseDtoToEntity(AsignaturaBaseDTO asignaturaBaseDTO){
        Asignatura asignatura = null;

        if(asignaturaBaseDTO != null 
        && asignaturaBaseDTO.getId() > 0){
            Optional<Asignatura> optAsignatura = asignaturaRepository
                .findById(asignaturaBaseDTO.getId());
            if (optAsignatura.isPresent()){
                asignatura = optAsignatura.orElseThrow();
            }
        }

        return asignatura;
    }

    public AsignaturaDTO entityToDTO(Asignatura asignatura){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        AsignaturaDTO asignaturaDTO = modelMapper.map(asignatura, AsignaturaDTO.class);        
        asignaturaDTO.setEstudio_id(asignatura.getEstudio_id());
        asignaturaDTO.setEstudioAlias(asignatura.getEstudioAlias());

        return asignaturaDTO;
    }
    
    public AsignaturaBaseDTO entityToBaseDTO(Asignatura asignatura){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        AsignaturaBaseDTO asignaturaBaseDTO = modelMapper.map(asignatura, AsignaturaBaseDTO.class);                

        return asignaturaBaseDTO;
    }

    public List<AsignaturaDTO> listToDTO(List<Asignatura> listaAsignaturas){
        return listaAsignaturas.stream()
                .map(asignatura -> this.entityToDTO(asignatura))
                .collect(Collectors.toList());
    }    
}
