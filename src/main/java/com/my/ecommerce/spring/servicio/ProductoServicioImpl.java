package com.my.ecommerce.spring.servicio;

import com.my.ecommerce.spring.entidades.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.my.ecommerce.spring.repositorios.IProductoRepositorio;

/**
 *
 * @author Raúl Gómez
 */
@Service
public class ProductoServicioImpl implements IProductoServicio {
    
    @Autowired
    private IProductoRepositorio productoRepositorio;
    
    @Override
    public Producto save(Producto producto) {
        return productoRepositorio.save(producto);
    }
    
    @Override
    public Optional<Producto> get(Integer id) {
        return productoRepositorio.findById(id);
    }
    
    @Override
    public void update(Producto producto) {
        productoRepositorio.save(producto);
    }
    
    @Override
    public void delete(Integer id) {
        productoRepositorio.deleteById(id);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepositorio.findAll();
    }
    
}

