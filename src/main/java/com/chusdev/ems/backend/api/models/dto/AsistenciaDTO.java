package com.chusdev.ems.backend.api.models.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Existe una Asistencia por cada alumno del grupo asociado a una Clase
 * 
 * Al crear una clase se generan por defecto las Asistencias correspondientes
 *  
 */
@Getter
@Setter
public class AsistenciaDTO {
    private Long id;

    private String comentario;

    private Long clase_id;

    private AlumnoBaseDTO alumno;
}
