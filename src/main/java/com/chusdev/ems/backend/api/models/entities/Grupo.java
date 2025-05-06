package com.chusdev.ems.backend.api.models.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Grupo al que pueden pertenecer profesores y alumnos (dependiendo del tipo)
 * Para los alumnos conforman los grupos de clase
 * Para los profesores conforman los distintos departamentos o seminarios
 * Extiende EntidadAuditable (campos de auditoría) ->
 * Extiende EntidadBase (campo Id)
 *  
 * @see EntidadAuditable
 * @see EntidadBase 
 */
@Getter
@Setter
@Entity
public class Grupo extends EntidadAuditable{    

    private byte tipo;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El alias no puede estar vacío")
    private String alias;

}
