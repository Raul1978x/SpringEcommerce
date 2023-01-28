package com.my.ecommerce.spring.repositorios;

import com.my.ecommerce.spring.entidades.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Raúl Gómez
 */
@Repository
public interface DetalleOrdenRepositorio extends JpaRepository<DetalleOrden, Integer>{
    
}
