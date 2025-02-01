package com.chusdev.ems.backend.api.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesorDTO {
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

    //Propiedades de Profesor
    private Long grupoId;
    private String cargo;

}
