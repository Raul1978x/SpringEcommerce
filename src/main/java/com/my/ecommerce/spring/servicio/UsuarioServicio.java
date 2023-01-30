package com.my.ecommerce.spring.servicio;

import com.my.ecommerce.spring.entidades.Usuario;
import java.util.Optional;

/**
 *
 * @author Raúl Gómez
 */

public interface UsuarioServicio {

   public Optional<Usuario> findById(Integer id);
}
