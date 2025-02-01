package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chusdev.ems.backend.api.models.dto.AlumnoDTO;
import com.chusdev.ems.backend.api.models.entities.Alumno;
import com.chusdev.ems.backend.api.models.dto.mapper.AlumnoMapper;
import com.chusdev.ems.backend.api.repositories.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService{

    @Autowired
    private AlumnoRepository repository;

    @Autowired
    private AlumnoMapper alumnoMapper;

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findAll() {
        return (List<Alumno>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Alumno> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Alumno save(Alumno alumno) {        
        return repository.save(alumno);
    }

    @Override
    @Transactional
    public Alumno save(AlumnoDTO alumnoDTO){
        return this.save(alumnoMapper.dtoToEntity(alumnoDTO));
    }

    @Override
    @Transactional
    public Optional<Alumno> update(Alumno alumno, Long id) {
        Optional<Alumno> optAlumno = this.findById(id);
        Alumno savedAlumno = null;
        if (optAlumno.isPresent()){
            Alumno dbAlumno = optAlumno.orElseThrow();
            BeanUtils.copyProperties(alumno, dbAlumno, "id");
            savedAlumno = this.save(dbAlumno);
        }

        return Optional.ofNullable(savedAlumno);
    }

    @Override
    @Transactional
    public Optional<Alumno> update(AlumnoDTO alumnoDTO, Long id) {
        return this.update(alumnoMapper.dtoToEntity(alumnoDTO), id);
    }
}
