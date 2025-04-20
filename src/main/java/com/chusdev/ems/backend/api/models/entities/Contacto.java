package com.chusdev.ems.backend.api.models.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Personas de contacto de los alumnos
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
public class Contacto extends Persona{    
    private int numContacto;
}
