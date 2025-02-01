package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import com.chusdev.ems.backend.api.models.dto.AlumnoDTO;
import com.chusdev.ems.backend.api.models.entities.Alumno;

public interface AlumnoService {
    List<Alumno> findAll();

    Optional<Alumno> findById(Long id);

    Alumno save(Alumno alumno);

    Alumno save(AlumnoDTO alumnoDTO);

    Optional<Alumno> update(Alumno alumno, Long id);

    Optional<Alumno> update(AlumnoDTO alumno, Long id);

    void delete(Long id);
}
