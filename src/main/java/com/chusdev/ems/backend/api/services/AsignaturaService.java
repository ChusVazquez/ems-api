package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import com.chusdev.ems.backend.api.models.dto.AsignaturaDTO;
import com.chusdev.ems.backend.api.models.entities.Asignatura;

public interface AsignaturaService {
    List<Asignatura> findAll();

    Optional<Asignatura> findById(Long id);

    Asignatura save(Asignatura asignatura);

    Asignatura save(AsignaturaDTO asignaturaDTO);

    Optional<Asignatura> update(Asignatura asignatura, Long id);

    Optional<Asignatura> update(AsignaturaDTO asignaturaDTO, Long id);

    void delete(Long id);
}
