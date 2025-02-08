package com.chusdev.ems.backend.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chusdev.ems.backend.api.models.dto.ClaseDTO;
import com.chusdev.ems.backend.api.models.dto.mapper.ClaseMapper;
import com.chusdev.ems.backend.api.models.entities.Alumno;
import com.chusdev.ems.backend.api.models.entities.Asistencia;
import com.chusdev.ems.backend.api.models.entities.Clase;
import com.chusdev.ems.backend.api.repositories.AlumnoRepository;
import com.chusdev.ems.backend.api.repositories.AsistenciaRepository;
import com.chusdev.ems.backend.api.repositories.ClaseRepository;

@Service
public class ClaseServiceImpl implements ClaseService{

    @Autowired
    ClaseRepository repository;

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    AsistenciaRepository asistenciaRepository;

    @Autowired
    ClaseMapper claseMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Clase> findAll() {
        return (List<Clase>) repository.findAll();
    }

    /**
     * Todas las clases de un horario
     * @param horarioId
     * @return List<Clase>
     */
    @Override
    @Transactional(readOnly = true)
    public List<Clase> findByHorarioId(Long horarioId){
        return repository.findByHorarioId(horarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Clase> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Clase save(Clase clase) {
        Clase savedClase = repository.save(clase);

        //Al crear o editar la clase
        //Si no tiene lista de asistencias, se genera
        if (savedClase != null && savedClase.getAsistencias() == null){
            this.generarAsistencias(savedClase.getId());
        } 

        return savedClase;
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

    /**
     * Se genera todo el registro de asistencia de la clase al crearla
     * Por defecto se presupone que todos los alumnos asisten
     * para que el profesor sólo tenga que marcar las faltas o retrasos
     */
    @Override
    @Transactional
    public List<Asistencia> generarAsistencias(Long claseId){
        List<Asistencia> listaAsistencias = null;
        
        Optional<Clase> optClase = repository.findById(claseId);
        if(optClase.isPresent()){
            Clase clase = optClase.orElseThrow();
            
            //Obtener lista de alumnos del grupo del horario
            if (clase.getHorario() != null 
                && clase.getHorario().getGrupo() != null){
                
                List<Alumno> alumnos = alumnoRepository
                    .findByGrupoId(clase.getHorario().getGrupo().getId());
                
                if (alumnos != null){
                    //Generar asistencia por cada alumno

                    listaAsistencias = alumnos.stream()
                        .map(alumno -> new Asistencia(clase, alumno))
                        .collect(Collectors.toList());

                    if (listaAsistencias != null){
                        asistenciaRepository.saveAll(listaAsistencias);
                    }

                }
            }


            
        }

        return listaAsistencias;
    }

}
