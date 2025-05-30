package com.chusdev.ems.backend.api.models.dto.mapper;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chusdev.ems.backend.api.models.dto.HorarioDTO;
import com.chusdev.ems.backend.api.models.entities.Horario;
import com.chusdev.ems.backend.api.repositories.AsignaturaRepository;
import com.chusdev.ems.backend.api.repositories.AulaRepository;
import com.chusdev.ems.backend.api.repositories.GrupoRepository;
import com.chusdev.ems.backend.api.repositories.ProfesorRepository;

@Component
public class HorarioMapper {
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

    /**
     * Mapea HorarioDTO a Horario.
     * Asignatura, Grupo y Profesor se cargan en base al Id
     * @param horarioDTO
     * @return Horario
     */
    public Horario dtoToEntity(HorarioDTO horarioDTO){
        Horario horario = modelMapper.map(horarioDTO, Horario.class);

        //El formato de la hora en HorarioDTO es sql.Time pero en Horario es java.LocalTime
        if (horarioDTO.getHora() != null){
            horario.setHora(horarioDTO.getHora().toLocalTime());
        }

        //Mapeo de Horario.Asignatura en base al Id de horarioDTO.AsignaturaBaseDTO.Id
        horario.setAsignatura(asignaturaMapper.baseDtoToEntity(horarioDTO.getAsignatura()));
        
        //Mapeo de Horario.Grupo en base al Id de horarioDTO.GrupoDTO.Id
        horario.setGrupo(grupoMapper.dtoIdToEntity(horarioDTO.getGrupo()));        

        //Mapeo de Horario.Profesor en base al Id de horarioDTO.ProfesorBaseDTO.Id
        horario.setProfesor(profesorMapper.baseDtoToEntity(horarioDTO.getProfesor()));
        
        //Mapeo de Horario.Aula en base al Id de horarioDTO.AulaDTO.Id
        horario.setAula(aulaMapper.dtoIdToEntity(horarioDTO.getAula()));

        return horario;
    }

    /**
     * Mapea Horario a HorarioDTO
     * 
     * @param horario
     * @return HorarioDTO
     */
    public HorarioDTO entityToDTO(Horario horario){
        HorarioDTO horarioDTO = modelMapper.map(horario, HorarioDTO.class);

        //El formato de la hora en HorarioDTO es sql.Time pero en Horario es java.LocalTime
        if (horario.getHora() != null){
            horarioDTO.setHora(Time.valueOf(horario.getHora()));
        }

        return horarioDTO;
    }

    /**
     * Mapea una lista de Horario a una lista de HorarioDTO
     * @param listaHorarios
     * @return List<HorarioDTO>
     */
    public List<HorarioDTO> listToDTO(List<Horario> listaHorarios){
        return listaHorarios.stream()
                .map(horario -> this.entityToDTO(horario))
                .collect(Collectors.toList());
    }

}
