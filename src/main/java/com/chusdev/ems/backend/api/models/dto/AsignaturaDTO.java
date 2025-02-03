package com.chusdev.ems.backend.api.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsignaturaDTO {
    private Long id;
    private String alias;
    private String nombre;

    private Long estudio_id;
}
