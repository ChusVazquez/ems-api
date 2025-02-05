package com.chusdev.ems.backend.api.models.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Datos básicos para incluir en objetos DTO que contengan Asignatura
 */
@Getter
@Setter
public class AsignaturaBaseDTO {
    private Long id;
    private String alias;    
}
