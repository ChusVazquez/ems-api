package com.chusdev.ems.backend.api.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactoDTO {
    private Long id;
    private int numContacto;
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
