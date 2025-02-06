package com.chusdev.ems.backend.api.models.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Contiene la información de cada una de las clases que se han impartido de un determinado Horario
 * La mayoría de los campos coinciden con Horario, pero se replican en una tabla aparte
 * porque los cambios en Horario no deben afectar retroactivamente a los elementos Clase ya creados 
 * (cambios de Profesor, Aula, etc...)
 * 
 * A diferencia del Horario, las Clases son eventos concretos
 * De manera que no tienen día de la semana, hora y duración
 * tienen fechaInicio y fechaFin
 * 
 * Extiende EntidadAuditable (campos de auditoría) ->
 * Extiende EntidadBase (campo Id)
 * 
 * @see Horario
 * @see EntidadAuditable
 * @see EntidadBase
 */
@Getter
@Setter
@Entity
public class Clase extends EntidadAuditable{    

    /**
     * Fecha y hora de inicio de la Clase
     */
    private Date fechaInicio;

    /**
     * Fecha y hora de finalización de la Clase
     */
    private Date fechaFin;

    /**
     * Un breve resumen de lo que se ha hecho en la clase
     */
    private String resumen;

    /**
     * Comentarios adicionales sobre la clase a nivel general:
     * Dificultades de los alumnos
     * Anotaciones para otros profesores de futuros cursos
     */
    private String comentario;

    /**
     * Asignatura impartida en esta clase
     */
    @ManyToOne
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;

    /**
     * Grupo de alumnos a los que se imparte esta clase
     */
    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    /**
     * Profesor que imparte esta clase
     */
    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    /**
     * Aula en la que se imparte esta clase
     */
    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;
}
