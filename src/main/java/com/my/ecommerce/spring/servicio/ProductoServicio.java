package com.my.ecommerce.spring.servicio;

import com.my.ecommerce.spring.entidades.Producto;
import java.util.Optional;

/**
 *
 * @author Raúl Gómez
 */


public interface ProductoServicio {

    public Producto save(Producto producto);
    public Optional<Producto> get(Integer id);
    public void update(Producto producto);
    public void delete(Integer id);
}
