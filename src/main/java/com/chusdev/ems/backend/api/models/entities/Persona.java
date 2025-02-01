package com.chusdev.ems.backend.api.models.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

//TODO Crear una clase abstracta para campos de auditoría y extenderla (ver AuditingEntityListener)
//https://mayankposts.medium.com/database-auditing-in-spring-boot-with-spring-security-context-and-spring-data-jpa-9215b43744bb

@Getter
@Setter
@MappedSuperclass
public abstract class Persona extends EntidadAuditable{    
    
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String documento;
    private Byte tipoDocumento;
    private String direccion;
    private String telefono;
    private String movil;
    private String email;
}
