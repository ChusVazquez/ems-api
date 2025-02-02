package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chusdev.ems.backend.api.models.dto.AulaDTO;
import com.chusdev.ems.backend.api.models.dto.mapper.AulaMapper;
import com.chusdev.ems.backend.api.models.entities.Aula;
import com.chusdev.ems.backend.api.repositories.AulaRepository;



@Service
public class AulaServiceImpl implements AulaService{

    @Autowired
    private AulaRepository repository;

    @Autowired
    private AulaMapper aulaMapper;
    
    @Override
    @Transactional(readOnly = true)
    public List<Aula> findAll() {
        return (List<Aula>) repository.findAll();        
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Aula> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Aula save(Aula aula) {
        return repository.save(aula);
    }

    @Override
    @Transactional
    public Aula save(AulaDTO aulaDTO) {
        return repository.save(aulaMapper.dtoToEntity(aulaDTO));
    }

    @Override
    @Transactional
    public Optional<Aula> update(Aula aula, Long id) {
        Optional<Aula> optAula = this.findById(id);
        Aula savedAula = null;
        if (optAula.isPresent()){
            Aula dbAula = optAula.orElseThrow();
            BeanUtils.copyProperties(aula, dbAula, "id");
            savedAula = this.save(dbAula);
        }
        return Optional.ofNullable(savedAula);        
    }

    @Override
    @Transactional
    public Optional<Aula> update(AulaDTO aulaDTO, Long id) {
        return this.update(aulaMapper.dtoToEntity(aulaDTO), id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
