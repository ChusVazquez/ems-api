package com.chusdev.ems.backend.api.models.entities;

import java.sql.Date;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class EntidadAuditable extends EntidadBase {
    private Date fechaCreacion;
    private Date fechaModificacion;
}
