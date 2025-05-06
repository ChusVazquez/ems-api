package com.chusdev.ems.backend.api.models.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El primer apellido no puede estar vacío")
    private String apellido1;

    private String apellido2;
    
    @NotBlank(message = "El documento no puede estar vacío")
    private String documento;
    private Byte tipoDocumento;
    
    @NotBlank
    private String direccion;
    
    @Pattern(regexp = "^[0-9]+$", message = "El teléfono solo puede contener números")
    private String telefono;

    @Pattern(regexp = "^[0-9]+$", message = "El móvil solo puede contener números")
    private String movil;

    @Email(message = "Debe tener formato e-mail")
    private String email;
}
