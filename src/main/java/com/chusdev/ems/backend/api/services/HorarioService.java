package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import com.chusdev.ems.backend.api.models.dto.HorarioDTO;
import com.chusdev.ems.backend.api.models.entities.Clase;
import com.chusdev.ems.backend.api.models.entities.Horario;

public interface HorarioService {
    List<Horario> findAll();

    Optional<Horario> findById(Long id);

    Horario save(Horario horario);

    Horario save(HorarioDTO horarioDTO);

    Optional<Horario> update(Horario horario, Long id);

    Optional<Horario> update(HorarioDTO horarioDTO, Long id);

    void delete(Long id);

    List<Clase> nextClases(Long horarioId, int num);

    List<Clase> getClases(Long horarioId);

}
