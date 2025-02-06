package com.chusdev.ems.backend.api.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chusdev.ems.backend.api.models.entities.Clase;

public interface ClaseRepository extends CrudRepository<Clase, Long>{
    
    /**
     * Todas las clases de un horario
     * @param horarioId
     * @return List<Clase>
     */
    List<Clase> findByHorarioId(Long horarioId);
    
    /**
     * Obtener la clase más reciente de un horario
     * @param horarioId
     * @return Clase
     */
    Clase findFirstByHorarioIdOrderByFechaInicioDesc(@Param("horarioId") Long horarioId);
}
