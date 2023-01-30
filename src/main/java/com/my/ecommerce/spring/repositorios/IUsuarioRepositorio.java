package com.my.ecommerce.spring.repositorios;

import com.my.ecommerce.spring.entidades.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Raúl Gómez
 */
@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);
}
