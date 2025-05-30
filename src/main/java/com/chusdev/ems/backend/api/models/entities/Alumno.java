package com.chusdev.ems.backend.api.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Alumnos del centro sobre los que trabaja el control de asistencia.
 * 
 * Extiende Persona (datos de persona genéricos) ->
 * Extiende EntidadAuditable (campos de auditoría) ->
 * Extiende EntidadBase (campo Id)
 * 
 * @see Persona
 * @see EntidadAuditable
 * @see EntidadBase
 */
@Getter
@Setter
@Entity
public class Alumno extends Persona{

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    private String idalu;
    private String ina;
    private String idea;    
        
}
