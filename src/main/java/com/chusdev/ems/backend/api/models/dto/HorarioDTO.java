package com.chusdev.ems.backend.api.models.dto;

import java.sql.Time;
import java.time.DayOfWeek;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HorarioDTO {

    private Long id;
    /**
     * Día de la semana representado en formato DayOfWeek
     */
    private DayOfWeek dia;

    /**
     * Hora a la que empieza la clase
     */
    // @JsonFormat(pattern = "HH:mm")
    private Time hora;

    /**
     * Duración de las clases de este horario
     */
    private int duracion;

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
}
