package com.chusdev.ems.backend.api.models.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Estudios a los que pertenece una Asignatura. Pueden ser ciclos formativos, bachillerato, ESO...
 * Extiende EntidadAuditable (campos de auditoría) ->
 * Extiende EntidadBase (campo Id)
 *  
 * @see EntidadAuditable
 * @see EntidadBase
 */
@Getter
@Setter
@Entity
public class Estudio extends EntidadAuditable{    
    private String alias;
    private String nombre;
    private String nivel;
}
