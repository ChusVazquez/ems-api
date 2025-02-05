package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chusdev.ems.backend.api.models.dto.HorarioDTO;
import com.chusdev.ems.backend.api.models.dto.mapper.HorarioMapper;
import com.chusdev.ems.backend.api.models.entities.Horario;
import com.chusdev.ems.backend.api.repositories.HorarioRepository;

@Service
public class HorarioServiceImpl implements HorarioService{

    @Autowired
    HorarioRepository repository;

    @Autowired
    HorarioMapper horarioMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Horario> findAll() {
        return (List<Horario>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Horario> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Horario save(Horario horario) {
        return repository.save(horario);
    }

    @Override
    public Horario save(HorarioDTO horarioDTO) {
        return this.save(horarioMapper.dtoToEntity(horarioDTO));
    }

    @Override
    public Optional<Horario> update(Horario horario, Long id) {
        Optional<Horario> optHorario = this.findById(id);
        Horario savedHorario = null;
        if (optHorario.isPresent()){
            Horario dbHorario = optHorario.orElseThrow();
            BeanUtils.copyProperties(horario, dbHorario, "id");
            savedHorario = this.save(dbHorario);
        }

        return Optional.ofNullable(savedHorario);
    }

    @Override
    @Transactional
    public Optional<Horario> update(HorarioDTO horarioDTO, Long id) {
        return this.update(horarioMapper.dtoToEntity(horarioDTO), id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
