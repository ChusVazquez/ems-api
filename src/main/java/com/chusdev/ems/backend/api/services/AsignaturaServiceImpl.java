package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chusdev.ems.backend.api.models.dto.AsignaturaDTO;
import com.chusdev.ems.backend.api.models.dto.mapper.AsignaturaMapper;
import com.chusdev.ems.backend.api.models.entities.Asignatura;
import com.chusdev.ems.backend.api.repositories.AsignaturaRepository;

@Service
public class AsignaturaServiceImpl implements AsignaturaService{

    @Autowired
    private AsignaturaRepository repository;

    @Autowired
    private AsignaturaMapper asignaturaMapper;

    
    /** 
     * @return List<Asignatura>
     */
    @Override
    @Transactional(readOnly = true)
    public List<Asignatura> findAll() {
        return (List<Asignatura>)repository.findAll();
    }

    
    /** 
     * @param id
     * @return Optional<Asignatura>
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Asignatura> findById(Long id) {
        return repository.findById(id);
    }

    
    /** 
     * @param asignatura
     * @return Asignatura
     */
    @Override
    @Transactional
    public Asignatura save(Asignatura asignatura) {
        return repository.save(asignatura);
    }

    
    /** 
     * @param asignaturaDTO
     * @return Asignatura
     */
    @Override
    @Transactional
    public Asignatura save(AsignaturaDTO asignaturaDTO) {
        return this.save(asignaturaMapper.dtoToEntity(asignaturaDTO));
    }

    @Override
    @Transactional
    public Optional<Asignatura> update(Asignatura asignatura, Long id) {
        Optional<Asignatura> optAsignatura = this.findById(id);
        Asignatura savedAsignatura = null;
        if (optAsignatura.isPresent()){
            Asignatura dbAsignatura = optAsignatura.orElseThrow();
            BeanUtils.copyProperties(asignatura, dbAsignatura, "id");
            savedAsignatura = this.save(dbAsignatura);                    
        }
        return Optional.ofNullable(savedAsignatura);
    }

    @Override
    @Transactional
    public Optional<Asignatura> update(AsignaturaDTO asignaturaDTO, Long id){
        return this.update(asignaturaMapper.dtoToEntity(asignaturaDTO), id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
