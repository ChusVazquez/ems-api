package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import com.chusdev.ems.backend.api.models.dto.ClaseDTO;
import com.chusdev.ems.backend.api.models.entities.Clase;

public interface ClaseService {
    List<Clase> findAll();

    Optional<Clase> findById(Long id);

    Clase save(Clase clase);

    Clase save(ClaseDTO claseDTO);

    Optional<Clase> update(Clase clase, Long id);

    Optional<Clase> update(ClaseDTO claseDTO, Long id);

    void delete(Long id);

}
