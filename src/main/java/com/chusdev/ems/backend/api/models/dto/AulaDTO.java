package com.chusdev.ems.backend.api.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AulaDTO {
    private Long id;
    private String alias;
    private String nombre;
    private String referencia;
}
