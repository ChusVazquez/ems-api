package com.chusdev.ems.backend.api.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Existe una Asistencia por cada alumno del grupo asociado a una Clase
 * 
 * Al crear una clase se generan por defecto las Asistencias correspondientes
 * 
 * Extiende EntidadAuditable (campos de auditoría) ->
 * Extiende EntidadBase (campo Id)
 * 
 * @see Clase
 * @see EntidadAuditable
 * @see EntidadBase
 */
@Getter
@Setter
@Entity
public class Asistencia extends EntidadAuditable{
    
    public Asistencia (Clase clase, Alumno alumno){
        this.clase = clase;
        this.alumno = alumno;
        this.estado = EstadoAsistencia.ASISTENCIA;
    }

    /**
     * Comentario por si algún estado de asistencia requiere una aclaración
     * Motivo de falta, retraso...
     */
    private String comentario;

    /**
     * Clase de la que se controla la asistencia
     */
    @ManyToOne
    @JoinColumn(name = "clase_id")
    private Clase clase;

    /**
     * Alumno del que se registra la asistencia a la clase
     */
    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    /**
     * Estado de la asistencia
     * Actualmente los estados son fijos pero
     * a futuro se podrán definir en una tabla configurable
     * 
     * 1 - Asistencia
     * 2 - Falta
     * 3 - Retraso
     * 4 - Falta justificada
     */
    private EstadoAsistencia estado;

    
    public enum EstadoAsistencia {
        ASISTENCIA(1),
        FALTA(2),
        RETRASO(3),
        FALTA_JUSTIFICADA(4);

        private final byte valor;

        EstadoAsistencia(int valor) {
            this.valor = (byte) valor;
        }

        public byte getValor() {
            return valor;
        }
        
        public static EstadoAsistencia fromValor(byte valor) {
            for (EstadoAsistencia estado : EstadoAsistencia.values()) {
                if (estado.getValor() == valor) {
                    return estado;
                }
            }
            throw new IllegalArgumentException("Valor no válido: " + valor);
        }
    }

}
