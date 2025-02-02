package com.chusdev.ems.backend.api.models.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Aula extends EntidadAuditable{
    private String alias;
    private String nombre;
    private String referencia;
}
