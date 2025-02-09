package com.chusdev.ems.backend.api.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chusdev.ems.backend.api.models.dto.HorarioDTO;
import com.chusdev.ems.backend.api.models.dto.mapper.HorarioMapper;
import com.chusdev.ems.backend.api.models.entities.Clase;
import com.chusdev.ems.backend.api.models.entities.Horario;
import com.chusdev.ems.backend.api.repositories.ClaseRepository;
import com.chusdev.ems.backend.api.repositories.HorarioRepository;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    HorarioRepository repository;

    @Autowired
    HorarioMapper horarioMapper;

    @Autowired
    ClaseRepository claseRepository;

    @Autowired
    ClaseService claseService;

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
    @Transactional
    public Horario save(HorarioDTO horarioDTO) {
        return this.save(horarioMapper.dtoToEntity(horarioDTO));
    }

    @Override
    @Transactional
    public Optional<Horario> update(Horario horario, Long id) {
        Optional<Horario> optHorario = this.findById(id);
        Horario savedHorario = null;
        if (optHorario.isPresent()) {
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

    /**
     * Genera las N clases siguientes de ese horario de forma automática
     * @param horarioId - Identificador del horario del que queremos generar clases
     * @param num - [Opcional] nº de clases a generar (por defecto 1)
     * @return List<Clase>
     */
    @Override
    @Transactional
    public List<Clase> nextClases(Long horarioId, int num) {
        List<Clase> listaClases = new ArrayList<>();
        Optional<Horario> optHorario = repository.findById(horarioId);
        if (optHorario.isPresent()) {
            Horario horario = optHorario.orElseThrow();
            
            //La fechaDesde por defecto será la fecha actual
            LocalDate fechaDesde = LocalDate.now();            
            Clase ultimaClase = claseRepository.findFirstByHorarioIdOrderByFechaInicioDesc(horarioId);
            if (ultimaClase != null){
                //Pero si existen clases se toma como fechaDesde la última clase
                fechaDesde = ultimaClase.getFechaInicio().toLocalDate();
            }

            // Se generan tantas clases como se indiquen en 'num'
            for (int i = 0; i < num; i++) {

                //Fecha y hora de inicio de la clase
                LocalDateTime inicioProximaClase = this.calcularProximaClase(horario, fechaDesde);

                //Fecha y hora de fin de la clase
                LocalDateTime finProximaClase = inicioProximaClase.plusMinutes(horario.getDuracion());

                //Creo la clase con las fechas calculadas + los datos del horario
                Clase clase = new Clase();
                
                clase.setFechaInicio(inicioProximaClase);
                clase.setFechaFin(finProximaClase);

                clase.setHorario(horario);
                clase.setAsignatura(horario.getAsignatura());
                clase.setAula(horario.getAula());
                clase.setGrupo(horario.getGrupo());
                clase.setProfesor(horario.getProfesor());
                
                claseService.save(clase);

                //Establezco como fechaDesde la fecha de la clase creada 
                //como base para calcular la siguiente
                fechaDesde = inicioProximaClase.toLocalDate().plusWeeks(1);
            }
        }

        return listaClases;
    }
    
    /**
     * Devuelve las clases asociadas a este horario
     */
    @Override
    public List<Clase> getClases(Long horarioId) {
        return claseRepository.findByHorarioId(horarioId);
    }

    // Funciones auxiliares
    public LocalDateTime calcularProximaClase(Horario horario, LocalDate fechaDesde) {
        // Calcular en qué fecha cae la próxima clase
        LocalDate proximoDia = fechaDesde.with(TemporalAdjusters.next(horario.getDia()));

        // Añadir la hora a la fecha de la última clase
        return LocalDateTime.of(proximoDia, horario.getHora());
    }    
}
