package com.chusdev.ems.backend.api.models.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad base para cualquier entidad que deba tener Id
 * Id: Long autogenerado de tipo IDENTITY
 */
@Getter
@Setter
@MappedSuperclass
public abstract class EntidadBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
