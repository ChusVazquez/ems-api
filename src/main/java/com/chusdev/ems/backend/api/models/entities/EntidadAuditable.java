package com.chusdev.ems.backend.api.models.entities;

import java.sql.Date;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad abstracta que se usa para que herede cualquier otra entidad
 * de la que queramos guardar datos de auditoria
 * TODO: Pendiente de detallar todos los datos de auditoria 
 * y de obtener los datos de UserDetailService
 * https://mayankposts.medium.com/database-auditing-in-spring-boot-with-spring-security-context-and-spring-data-jpa-9215b43744bb
 * Extiende EntidadBase, por lo que hace que todas las clases que heredan
 * tengan el campo Id de serie
 * 
 * {@see EntidadBase} 
 */
@Getter
@Setter
@MappedSuperclass
public class EntidadAuditable extends EntidadBase {
    private Date fechaCreacion;
    private Date fechaModificacion;
}
