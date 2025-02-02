package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import com.chusdev.ems.backend.api.models.dto.EstudioDTO;
import com.chusdev.ems.backend.api.models.entities.Estudio;

public interface EstudioService {
    List<Estudio> findAll();

    Optional<Estudio> findById(Long id);

    Estudio save(Estudio estudio);

    Estudio save(EstudioDTO estudioDTO);

    Optional<Estudio> update(Estudio estudio, Long id);

    Optional<Estudio> update(EstudioDTO estudioDTO, Long id);

    void delete(Long id);
}
