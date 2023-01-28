package com.my.ecommerce.spring.repositorios;

import com.my.ecommerce.spring.entidades.Orden;
import com.my.ecommerce.spring.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Raúl Gómez
 */
@Repository
public interface OrdenRepositorio extends JpaRepository<Orden, Integer> {

    List<Orden> findByUsuario(Usuario usuario);
}
