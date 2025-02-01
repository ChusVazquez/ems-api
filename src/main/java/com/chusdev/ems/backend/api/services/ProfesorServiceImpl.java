package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chusdev.ems.backend.api.models.dto.ProfesorDTO;
import com.chusdev.ems.backend.api.models.entities.Profesor;
import com.chusdev.ems.backend.api.models.dto.mapper.ProfesorMapper;
import com.chusdev.ems.backend.api.repositories.ProfesorRepository;

@Service
public class ProfesorServiceImpl implements ProfesorService{

    @Autowired
    ProfesorRepository repository;

    @Autowired
    ProfesorMapper profesorMapper;

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Profesor> findAll() {
        return (List<Profesor>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Profesor> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Profesor save(Profesor profesor) {        
        return repository.save(profesor);
    }

    @Override
    @Transactional
    public Profesor save(ProfesorDTO profesorDTO){
        return this.save(profesorMapper.dtoToEntity(profesorDTO));
    }

    @Override
    @Transactional
    public Optional<Profesor> update(Profesor profesor, Long id) {
        Optional<Profesor> optProfesor = this.findById(id);
        Profesor savedProfesor = null;
        if (optProfesor.isPresent()){
            Profesor dbProfesor = optProfesor.orElseThrow();
            BeanUtils.copyProperties(profesor, dbProfesor, "id");
            savedProfesor = this.save(dbProfesor);
        }

        return Optional.ofNullable(savedProfesor);
    }

    @Override
    @Transactional
    public Optional<Profesor> update(ProfesorDTO profesorDTO, Long id) {
        return this.update(profesorMapper.dtoToEntity(profesorDTO), id);
    }
}
