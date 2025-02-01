package com.chusdev.ems.backend.api.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumnoDTO {
    //Propiedades de Persona
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String documento;
    private Byte tipoDocumento;
    private String direccion;
    private String telefono;
    private String movil;
    private String email;

    //Propiedades de Alumno
    private Long id;

    private Long grupoId;

    private String idalu;
    private String ina;
    private String idea;
    
    private String nombreContacto1;
    private String apellidosContacto1;
    private String documentoContacto1;
    private String telefonoContacto1;
    private String emailContacto1;

    private String nombreContacto2;
    private String apellidosContacto2;
    private String documentoContacto2;
    private String telefonoContacto2;
    private String emailContacto2;

}
