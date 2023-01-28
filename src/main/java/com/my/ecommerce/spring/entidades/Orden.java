package com.my.ecommerce.spring.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Raúl Gómez
 */
@Data
@Entity
@Table(name = "ordenes")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numero;
    
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    
    @Temporal(TemporalType.DATE)
    private Date fechaRecibida;
    
    private double total;
    
    @ManyToOne
    private Usuario usuario;
    
    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detalle;
    
}
