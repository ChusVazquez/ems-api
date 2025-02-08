package com.chusdev.ems.backend.api.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.chusdev.ems.backend.api.models.entities.Asistencia;

public interface AsistenciaRepository extends CrudRepository<Asistencia, Long>{
    /**
     * Todas las asistencias de una clase
     * @param claseId
     * @return List<Asistencia>
     */
    List<Asistencia> findByClaseId(Long claseId);
}
