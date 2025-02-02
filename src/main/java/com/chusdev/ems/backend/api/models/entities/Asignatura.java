package com.chusdev.ems.backend.api.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Asignatura extends EntidadAuditable{

    private String alias;
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name="estudio_id")
    private Estudio estudio;
}
