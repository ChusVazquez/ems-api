package com.chusdev.ems.backend.api.models.dto.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chusdev.ems.backend.api.models.dto.AlumnoBaseDTO;
import com.chusdev.ems.backend.api.models.dto.AlumnoDTO;
import com.chusdev.ems.backend.api.models.entities.Alumno;
import com.chusdev.ems.backend.api.models.entities.Grupo;
import com.chusdev.ems.backend.api.repositories.AlumnoRepository;
import com.chusdev.ems.backend.api.repositories.ContactoRepository;
import com.chusdev.ems.backend.api.repositories.GrupoRepository;

@Component
public class AlumnoMapper {

    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    GrupoRepository grupoRepository;

    @Autowired
    ContactoRepository contactoRepository;

    @Autowired
    AlumnoRepository alumnoRepository;

    public Alumno dtoToEntity(AlumnoDTO alumnoDTO){
        Alumno alumno = modelMapper.map(alumnoDTO, Alumno.class);

        if (alumnoDTO.getGrupoId() != null && alumnoDTO.getGrupoId() > 0)
        {
            Optional<Grupo> grupoAlumno = grupoRepository.findById(alumnoDTO.getGrupoId());
            if (grupoAlumno.isPresent())
            {
                alumno.setGrupo(grupoAlumno.get());
            }
        }        

        return alumno;
    }

    public Alumno baseDtoToEntity(AlumnoBaseDTO alumnoBaseDTO){
        Alumno alumno = null;

        if(alumnoBaseDTO != null
            && alumnoBaseDTO.getId() > 0){
            
            Optional<Alumno> optAlumno = alumnoRepository
                .findById(alumnoBaseDTO.getId());
            if (optAlumno.isPresent()){
                alumno = optAlumno.orElseThrow();
            }
        }

        return alumno;
    }

    public AlumnoDTO entityToDTO(Alumno alumno){
        AlumnoDTO alumnoDTO = modelMapper.map(alumno, AlumnoDTO.class);

        Grupo grupoAlumno = alumno.getGrupo();
        if (grupoAlumno != null)
        {
            alumnoDTO.setGrupoId(grupoAlumno.getId());
        }        

        return alumnoDTO;
    }

    public AlumnoBaseDTO entityToBaseDTO(Alumno alumno){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        AlumnoBaseDTO alumnoBaseDTO = modelMapper.map(alumno, AlumnoBaseDTO.class);

        return alumnoBaseDTO;
    }

    public List<AlumnoDTO> listToDTO(List<Alumno> listaAlumnos){
        return listaAlumnos.stream()
                .map(alumno -> this.entityToDTO(alumno))
                .collect(Collectors.toList());
    }
}
