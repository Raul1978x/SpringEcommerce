package com.my.ecommerce.spring.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Raúl Gómez
 */

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String direccion;
    private String email;
    private String username;
    private String password;
    private String telefono;
    private String tipo;
    
    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;
    
    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;
}
