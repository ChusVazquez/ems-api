package com.chusdev.ems.backend.api.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.chusdev.ems.backend.api.models.entities.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long>{
    /**
     * Todos los alumnos de un grupo
     * 
     * @param grupoId
     * @return List<Alumno>
     */
    List<Alumno> findByGrupoId(Long grupoId);
}
