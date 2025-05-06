package com.chusdev.ems.backend.api.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumnoDTO {
    //Propiedades de Persona    
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

    //Propiedades de Alumno
    private Long id;

    private Long grupoId;

    private String idalu;
    private String ina;
    private String idea;    
}
