package com.chusdev.ems.backend.api.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Profesor para asignar a los horarios y clases impartidas
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
public class Profesor extends Persona{    

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;
    private String cargo;

}
