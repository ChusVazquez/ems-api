package com.chusdev.ems.backend.api.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Profesor extends Persona{    

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;
    private String cargo;

}
