package com.chusdev.ems.backend.api.models.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


/**
 * Contiene los campos comunes de personas para ser heredados por Alumno, Profesor...
 * Extiende EntidadAuditable (campos de auditoría) ->
 * Extiende EntidadBase (campo Id)
 * 
 * @see EntidadAuditable
 * @see EntidadBase
 */
@Getter
@Setter
@MappedSuperclass
public abstract class Persona extends EntidadAuditable{    
    
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String documento;
    private Byte tipoDocumento;
    private String direccion;
    private String telefono;
    private String movil;
    private String email;
}
