package com.chusdev.ems.backend.api.models.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class EntidadAuditable extends EntidadBase {    

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "usr_creacion", updatable = false)
    private User usuarioCreacion;

    @CreatedDate
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "usr_modificacion")
    private User usuarioModificacion;    

    @LastModifiedDate
    @Column(name = "fecha_modificacion")    
    private LocalDateTime fechaModificacion;
}
