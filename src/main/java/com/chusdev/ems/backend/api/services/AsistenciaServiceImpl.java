package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chusdev.ems.backend.api.models.dto.AsistenciaDTO;
import com.chusdev.ems.backend.api.models.dto.mapper.AsistenciaMapper;
import com.chusdev.ems.backend.api.models.entities.Asistencia;
import com.chusdev.ems.backend.api.repositories.AsistenciaRepository;

@Service
public class AsistenciaServiceImpl implements AsistenciaService{

    @Autowired
    AsistenciaRepository repository;

    @Autowired
    AsistenciaMapper asistenciaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Asistencia> findAll() {
        return (List<Asistencia>) repository.findAll();
    }

    /**
     * Todas las asistencias de una clase
     * @param claseId
     * @return List<Asistencia>
     */
    @Override
    @Transactional(readOnly = true)
    public List<Asistencia> findByClaseId(Long claseId) {
        return repository.findByClaseId(claseId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Asistencia> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Asistencia save(Asistencia asistencia) {
        return repository.save(asistencia);
    }

    @Override
    @Transactional
    public Asistencia save(AsistenciaDTO asistenciaDTO) {
        return this.save(asistenciaMapper.dtoToEntity(asistenciaDTO));
    }

    @Override
    @Transactional
    public Optional<Asistencia> update(Asistencia asistencia, Long id) {
        Optional<Asistencia> optAsistencia = this.findById(id);
        Asistencia savedAsistencia = null;
        if (optAsistencia.isPresent()){
            Asistencia dbAsistencia = optAsistencia.orElseThrow();
            BeanUtils.copyProperties(asistencia, dbAsistencia, "id");
            savedAsistencia = this.save(dbAsistencia);
        }

        return Optional.ofNullable(savedAsistencia);
    }

    @Override
    @Transactional
    public Optional<Asistencia> update(AsistenciaDTO asistenciaDTO, Long id) {
        return this.update(asistenciaMapper.dtoToEntity(asistenciaDTO), id);
    }

    @Override
    @Transactional
    public Optional<Asistencia> setEstado(Long asistenciaId, byte estado) {
        Asistencia asistencia = null;
        
        Optional<Asistencia> optAsistencia = repository.findById(asistenciaId);
        if (optAsistencia.isPresent()){
            asistencia = optAsistencia.orElseThrow();
            asistencia.setEstado(estado);
            asistencia = this.save(asistencia); 
        }

        return Optional.ofNullable(asistencia);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }    

}
