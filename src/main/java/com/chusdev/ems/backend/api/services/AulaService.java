package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import com.chusdev.ems.backend.api.models.dto.AulaDTO;
import com.chusdev.ems.backend.api.models.entities.Aula;

public interface AulaService {
    List<Aula> findAll();

    Optional<Aula> findById(Long id);

    Aula save(Aula aula);

    Aula save(AulaDTO aulaDTO);

    Optional<Aula> update(Aula aula, Long id);

    Optional<Aula> update(AulaDTO aulaDTO, Long id);

    void delete(Long id);
}
