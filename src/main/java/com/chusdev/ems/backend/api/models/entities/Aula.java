package com.chusdev.ems.backend.api.models.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Aulas del centro en la que se pueden realizar Clases
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
public class Aula extends EntidadAuditable{
    private String alias;
    private String nombre;
    private String referencia;
}
