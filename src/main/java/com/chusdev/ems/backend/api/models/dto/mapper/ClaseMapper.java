package com.chusdev.ems.backend.api.models.dto.mapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chusdev.ems.backend.api.models.dto.ClaseDTO;
import com.chusdev.ems.backend.api.models.entities.Clase;
import com.chusdev.ems.backend.api.models.entities.Horario;
import com.chusdev.ems.backend.api.repositories.AsignaturaRepository;
import com.chusdev.ems.backend.api.repositories.AulaRepository;
import com.chusdev.ems.backend.api.repositories.GrupoRepository;
import com.chusdev.ems.backend.api.repositories.HorarioRepository;
import com.chusdev.ems.backend.api.repositories.ProfesorRepository;


@Component
public class ClaseMapper {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AsignaturaMapper asignaturaMapper;

    @Autowired
    GrupoMapper grupoMapper;

    @Autowired
    ProfesorMapper profesorMapper;

    @Autowired
    AulaMapper aulaMapper;

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    GrupoRepository grupoRepository;

    @Autowired
    AulaRepository aulaRepository;

    @Autowired
    HorarioRepository horarioRepository;

    /**
     * Mapea ClaseDTO a Clase.
     * Asignatura, Grupo y Profesor se cargan en base al Id
     * @param claseDTO
     * @return Clase
     */
    public Clase dtoToEntity(ClaseDTO claseDTO){
        Clase clase = modelMapper.map(claseDTO, Clase.class);

        //El formato de la fecha en ClaseDTO es sql.Date pero en Clase es java.LocalDateTime
        if (claseDTO.getFechaInicio() != null){
            clase.setFechaInicio(claseDTO.getFechaInicio().toLocalDateTime());
        }

        if (claseDTO.getFechaFin() != null){
            clase.setFechaFin(claseDTO.getFechaFin().toLocalDateTime());
        }

        //Mapeo de Clase.Asignatura en base al Id de claseDTO.AsignaturaBaseDTO.Id
        clase.setAsignatura(asignaturaMapper.baseDtoToEntity(claseDTO.getAsignatura()));
        
        //Mapeo de Clase.Grupo en base al Id de claseDTO.GrupoDTO.Id
        clase.setGrupo(grupoMapper.dtoToEntity(claseDTO.getGrupo()));        

        //Mapeo de Clase.Profesor en base al Id de claseDTO.ProfesorBaseDTO.Id
        clase.setProfesor(profesorMapper.baseDtoToEntity(claseDTO.getProfesor()));
        
        //Mapeo de Clase.Aula en base al Id de claseDTO.AulaDTO.Id
        clase.setAula(aulaMapper.dtoToEntity(claseDTO.getAula()));

        //Mapeo de Clase.Horario en base a claseDTO.horario_id
        Optional<Horario> optHorario = horarioRepository.findById(claseDTO.getHorario_id());
        if (optHorario.isPresent()){
            clase.setHorario(optHorario.orElseThrow());
        }
        return clase;
    }

    /**
     * Mapea Clase a ClaseDTO
     * 
     * @param clase
     * @return ClaseDTO
     */
    public ClaseDTO entityToDTO(Clase clase){
        ClaseDTO claseDTO = modelMapper.map(clase, ClaseDTO.class);

        //El formato de la fecha en ClaseDTO es sql.Date pero en Clase es java.LocalDateTime
        if (clase.getFechaInicio() != null){
            claseDTO.setFechaInicio(Timestamp.valueOf(clase.getFechaInicio()));
        }

        if (clase.getFechaFin() != null){
            claseDTO.setFechaFin(Timestamp.valueOf(clase.getFechaFin()));
        }
        
        if (clase.getHorario() != null){
            claseDTO.setHorario_id(clase.getHorario().getId());
        }

        return claseDTO;
    }

    /**
     * Mapea una lista de Clase a una lista de ClaseDTO
     * @param listaClases
     * @return List<ClaseDTO>
     */
    public List<ClaseDTO> listToDTO(List<Clase> listaClases){
        return listaClases.stream()
                .map(clase -> this.entityToDTO(clase))
                .collect(Collectors.toList());
    }
}
