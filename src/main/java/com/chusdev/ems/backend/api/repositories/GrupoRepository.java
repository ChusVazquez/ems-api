package com.chusdev.ems.backend.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.chusdev.ems.backend.api.models.entities.Grupo;

public interface GrupoRepository extends CrudRepository<Grupo, Long>{
    
}
