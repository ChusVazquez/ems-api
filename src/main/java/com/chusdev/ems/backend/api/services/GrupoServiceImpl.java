package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chusdev.ems.backend.api.models.entities.Grupo;
import com.chusdev.ems.backend.api.repositories.GrupoRepository;

@Service
public class GrupoServiceImpl implements GrupoService{

    @Autowired
    private GrupoRepository repository;

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Grupo> findAll() {
        return (List<Grupo>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Grupo> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Grupo save(Grupo grupo) {
        return repository.save(grupo);
    }

    @Override
    @Transactional
    public Optional<Grupo> update(Grupo grupo, Long id) {
        Optional<Grupo> optGrupo = this.findById(id);
        Grupo savedGrupo = null;
        if (optGrupo.isPresent()){
            Grupo dbGrupo = optGrupo.orElseThrow();
            //Copio todas las propiedades a excepción del id
            BeanUtils.copyProperties(grupo, dbGrupo, "id");            
            savedGrupo = this.save(dbGrupo);
        }
        return Optional.ofNullable(savedGrupo);
    }    
}
