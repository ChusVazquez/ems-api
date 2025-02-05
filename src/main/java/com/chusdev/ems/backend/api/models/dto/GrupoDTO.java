package com.chusdev.ems.backend.api.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoDTO {
    private Long id;
    private byte tipo;
    private String nombre;
    private String alias;
}
