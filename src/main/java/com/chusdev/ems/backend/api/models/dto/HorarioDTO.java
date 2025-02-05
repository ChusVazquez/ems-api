package com.chusdev.ems.backend.api.models.dto;

import java.sql.Time;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase DTO para Horario
 */
@Getter
@Setter
public class HorarioDTO {
    /**
     * Día de la semana representado por un número del 1(Lunes) al 7(Domingo)
     */
    private Byte dia;

    /**
     * Hora a la que empieza la clase
     */
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
}
