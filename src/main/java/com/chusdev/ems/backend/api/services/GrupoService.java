package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import com.chusdev.ems.backend.api.models.entities.Grupo;

public interface GrupoService {
    List<Grupo> findAll();

    Optional<Grupo> findById(Long id);

    Grupo save(Grupo grupo);

    Optional<Grupo> update(Grupo grupo, Long id);

    void delete(Long id);
}
