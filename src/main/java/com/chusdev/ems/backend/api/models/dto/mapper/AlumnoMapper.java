package com.chusdev.ems.backend.api.models.dto.mapper;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chusdev.ems.backend.api.models.dto.AlumnoDTO;
import com.chusdev.ems.backend.api.models.entities.Alumno;
import com.chusdev.ems.backend.api.models.entities.Grupo;
import com.chusdev.ems.backend.api.repositories.GrupoRepository;

@Component
public class AlumnoMapper {

    @Autowired
    GrupoRepository grupoRepository;

    @Autowired
    ModelMapper modelMapper;

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

    public AlumnoDTO entityToDTO(Alumno alumno){
        AlumnoDTO alumnoDTO = modelMapper.map(alumno, AlumnoDTO.class);

        Grupo grupoAlumno = alumno.getGrupo();
        if (grupoAlumno != null)
        {
            alumnoDTO.setGrupoId(grupoAlumno.getId());
        }

        return alumnoDTO;
    }
}
