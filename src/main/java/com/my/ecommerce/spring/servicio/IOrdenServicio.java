package com.my.ecommerce.spring.servicio;

import com.my.ecommerce.spring.entidades.Orden;
import com.my.ecommerce.spring.entidades.Usuario;
import java.util.List;

/**
 *
 * @author Raúl Gómez
 */

public interface IOrdenServicio {

    public List<Orden> findAll();
    public Orden save(Orden orden);
    public String generarNumeroOrden();
     List<Orden> findByUsuario(Usuario usuario);
}
