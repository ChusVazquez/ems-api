package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import com.chusdev.ems.backend.api.models.dto.AsistenciaDTO;
import com.chusdev.ems.backend.api.models.entities.Asistencia;

public interface AsistenciaService {
    List<Asistencia> findAll();

    Optional<Asistencia> findById(Long id);

    Asistencia save(Asistencia asistencia);

    Asistencia save(AsistenciaDTO asistenciaDTO);

    Optional<Asistencia> update(Asistencia asistencia, Long id);

    Optional<Asistencia> update(AsistenciaDTO asistenciaDTO, Long id);

    Optional<Asistencia> setEstado(Long asistenciaId, byte estado);

    void delete(Long id);


    /**
     * Todas las asistencias de una clase
     * @param claseId
     * @return List<Asistencia>
     */
    List<Asistencia> findByClaseId(Long claseId);

}
