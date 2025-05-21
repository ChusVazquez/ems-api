package com.chusdev.ems.backend.api.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumnoBaseDTO {
    public Long id;
    public String nombre;
    public String apellido1;
    public String apellido2;
    public GrupoDTO grupo;
}
