package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import com.chusdev.ems.backend.api.models.dto.ProfesorDTO;
import com.chusdev.ems.backend.api.models.entities.Profesor;

public interface ProfesorService {
    List<Profesor> findAll();

    Optional<Profesor> findById(Long id);

    Profesor save(Profesor profesor);

    Profesor save(ProfesorDTO profesorDTO);

    Optional<Profesor> update(Profesor profesor, Long id);

    Optional<Profesor> update(ProfesorDTO profesorDTO, Long id);

    void delete(Long id);
}
