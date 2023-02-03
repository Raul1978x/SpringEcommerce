package com.my.ecommerce.spring.servicio;

import com.my.ecommerce.spring.entidades.DetalleOrden;
import com.my.ecommerce.spring.repositorios.IDetalleOrdenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Raúl Gómez
 */
@Service
public class DetalleOrdenServicioImpl implements IDetalleOrdenServicio {

    @Autowired
    private IDetalleOrdenRepositorio detalleOrdenRepositorio;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenRepositorio.save(detalleOrden);
    }

}
