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

    public Long getEstudio_id(){
        Long estudio_id = null;

        if(this.estudio != null){
            estudio_id = estudio.getId();
        }

        return estudio_id;
    }

    public String getEstudioAlias(){
        String estudioAlias = new String("");

        if (this.estudio != null){
            estudioAlias = estudio.getAlias();
        }

        return estudioAlias;
    }
}
