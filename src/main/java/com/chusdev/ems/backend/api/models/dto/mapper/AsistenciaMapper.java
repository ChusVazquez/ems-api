package com.chusdev.ems.backend.api.models.dto.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chusdev.ems.backend.api.models.dto.AsistenciaDTO;
import com.chusdev.ems.backend.api.models.entities.Asistencia;
import com.chusdev.ems.backend.api.models.entities.Clase;
import com.chusdev.ems.backend.api.repositories.ClaseRepository;

@Component
public class AsistenciaMapper {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AlumnoMapper alumnoMapper;

    @Autowired
    ProfesorMapper profesorMapper;

    @Autowired
    ClaseRepository claseRepository;

    /**
     * Mapea AsistenciaDTO a Asistencia
     * @param asistenciaDTO
     * @return Asistencia
     */
    public Asistencia dtoToEntity(AsistenciaDTO asistenciaDTO){
        Asistencia asistencia = modelMapper.map(asistenciaDTO, Asistencia.class);

        if(asistenciaDTO.getClase_id() != null){
            Optional<Clase> optClase = claseRepository.findById(asistenciaDTO.getClase_id());
            if (optClase.isPresent()){
                asistencia.setClase(optClase.orElseThrow());
            }            
        }
        
        asistencia.setAlumno(alumnoMapper.baseDtoToEntity(asistenciaDTO.getAlumno()));

        asistencia.setProfesor(profesorMapper.baseDtoToEntity(asistenciaDTO.getProfesor()));        

        return asistencia;
    }

    public AsistenciaDTO entityToDto(Asistencia asistencia){
        AsistenciaDTO asistenciaDTO = modelMapper.map(asistencia, AsistenciaDTO.class);

        asistenciaDTO.setAlumno(alumnoMapper.entityToBaseDTO(asistencia.getAlumno()));

        asistenciaDTO.setProfesor(profesorMapper.entityToBaseDTO(asistencia.getProfesor()));

        asistenciaDTO.setClase_id(asistencia.getAlumno().getId());

        return asistenciaDTO;
    }

    public List<AsistenciaDTO> listToDTO(List<Asistencia> listaAsistencias){
        return listaAsistencias.stream()
                .map(asistencia -> this.entityToDto(asistencia))
                .collect(Collectors.toList());
    }
}
