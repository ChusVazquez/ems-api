package com.chusdev.ems.backend.api.models.entities;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 * Cada instancia de la entidad horario corresponde a
 * una calendarización de las horas de clase
 * Sirve para que un profesor tenga una lista de
 * las horas a las que imparte clases de cada asignatura y
 * para generar clases a partir de este modelo
 * 
 * Actualmente en el front no existe una vista de calendario
 * pero se diseña con previsión de que se añada a futuro
 * 
 * Extiende EntidadAuditable (campos de auditoría) ->
 * Extiende EntidadBase (campo Id)
 *  
 * @see EntidadAuditable
 * @see EntidadBase
 */

@Getter
@Setter
@Entity
public class Horario extends EntidadAuditable{
    /**
     * Día de la semana representado (en formato DayOfWeek para facilitar cálculos de fechas)
     */
    private DayOfWeek dia;

    /**
     * Hora a la que empieza la clase (en formato LocalTime para facilitar cálculos de fechas)
     */
    private LocalTime hora;

    /**
     * Duración de las clases de este horario
     */
    private int duracion;

    /**
     * Asignatura impartida en las clases de este horario
     */
    @ManyToOne
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;

    /**
     * Grupo de alumnos a los que se imparten las clases de este horario
     */
    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    /**
     * Profesor que imparte la clase de este horario
     */
    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    /**
     * Aula en la que se imparte la clase de este horario
     */
    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;

    /**
     * Lista de clases que dependen del horario
     * FechType.LAZY para que sólo lo recupere si se hace un get
     */
    @OneToMany(mappedBy = "horario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Clase> clases;

}
