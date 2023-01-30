package com.my.ecommerce.spring.servicio;

import com.my.ecommerce.spring.entidades.Producto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Raúl Gómez
 */


public interface IProductoServicio {

    public Producto save(Producto producto);
    public Optional<Producto> get(Integer id);
    public void update(Producto producto);
    public void delete(Integer id);
    public List<Producto> findAll();
}
