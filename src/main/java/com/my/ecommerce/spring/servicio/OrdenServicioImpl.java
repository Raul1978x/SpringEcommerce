package com.my.ecommerce.spring.servicio;

import com.my.ecommerce.spring.entidades.Orden;
import com.my.ecommerce.spring.repositorios.IOrdenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Raúl Gómez
 */
@Service
public class OrdenServicioImpl implements IOrdenServicio{

    @Autowired
    private IOrdenRepositorio iOrdenRepositorio;
    
    @Override
    public Orden save(Orden orden) {
        return iOrdenRepositorio.save(orden);
    }

    
}
