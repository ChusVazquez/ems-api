package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chusdev.ems.backend.api.models.dto.EstudioDTO;
import com.chusdev.ems.backend.api.models.dto.mapper.EstudioMapper;
import com.chusdev.ems.backend.api.models.entities.Estudio;
import com.chusdev.ems.backend.api.repositories.EstudioRepository;

@Service
public class EstudioServiceImpl implements EstudioService{

    @Autowired
    private EstudioRepository repository;

    @Autowired
    private EstudioMapper estudioMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Estudio> findAll() {
        return (List<Estudio>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Estudio> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Estudio save(Estudio estudio) {
        return repository.save(estudio);
    }

    @Override
    @Transactional
    public Estudio save(EstudioDTO estudioDTO) {
        return this.save(estudioMapper.dtoToEntity(estudioDTO));
    }

    @Override
    @Transactional
    public Optional<Estudio> update(Estudio estudio, Long id) {
        Optional<Estudio> optEstudio = this.findById(id);
        Estudio savedEstudio = null;
        if (optEstudio.isPresent()){
            Estudio dbEstudio = optEstudio.orElseThrow();
            BeanUtils.copyProperties(estudio, dbEstudio, "id");
            savedEstudio = this.save(dbEstudio);
        }

        return Optional.ofNullable(savedEstudio);
    }

    @Override
    @Transactional
    public Optional<Estudio> update(EstudioDTO estudioDTO, Long id){
        return this.update(estudioMapper.dtoToEntity(estudioDTO), id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    
}
