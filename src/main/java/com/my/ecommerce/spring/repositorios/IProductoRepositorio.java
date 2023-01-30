package com.my.ecommerce.spring.repositorios;

import com.my.ecommerce.spring.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Raúl Gómez
 */

@Repository
public interface IProductoRepositorio extends JpaRepository<Producto, Integer>{
    
}
