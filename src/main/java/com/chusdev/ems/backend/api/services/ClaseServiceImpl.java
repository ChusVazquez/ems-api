package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chusdev.ems.backend.api.models.dto.ClaseDTO;
import com.chusdev.ems.backend.api.models.dto.mapper.ClaseMapper;
import com.chusdev.ems.backend.api.models.entities.Clase;
import com.chusdev.ems.backend.api.repositories.ClaseRepository;

@Service
public class ClaseServiceImpl implements ClaseService{

    @Autowired
    ClaseRepository repository;

    @Autowired
    ClaseMapper claseMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Clase> findAll() {
        return (List<Clase>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Clase> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Clase save(Clase clase) {
        return repository.save(clase);
    }

    @Override
    @Transactional
    public Clase save(ClaseDTO claseDTO) {
        return this.save(claseMapper.dtoToEntity(claseDTO));
    }

    @Override
    @Transactional
    public Optional<Clase> update(Clase clase, Long id) {
        Optional<Clase> optClase = this.findById(id);
        Clase savedClase = null;
        if (optClase.isPresent()){
            Clase dbClase = optClase.orElseThrow();
            BeanUtils.copyProperties(clase, dbClase, "id");
            savedClase = this.save(dbClase);
        }
        
        return Optional.ofNullable(savedClase);
    }

    @Override
    @Transactional
    public Optional<Clase> update(ClaseDTO claseDTO, Long id) {
        return this.update(claseMapper.dtoToEntity(claseDTO), id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
