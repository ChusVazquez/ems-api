package com.chusdev.ems.backend.api.models.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaseDTO {

    private Long id;

    /**
     * Fecha y hora de inicio de la Clase
     */
    private Timestamp fechaInicio;

    /**
     * Fecha y hora de finalización de la Clase
     */
    private Timestamp fechaFin;

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
     * Datos básicos de la asignatura
     */    
    private AsignaturaBaseDTO asignatura;

    /**
     * Datos básicos del grupo
     */
    private GrupoDTO grupo;

    /**
     * Datos básicos del profesor
     */
    private ProfesorBaseDTO profesor;

    /**
     * Datos básicos del aula
     */
    private AulaDTO aula;

    /**
     * Sólo el ID del horario
     */
    private Long horario_id;
}
