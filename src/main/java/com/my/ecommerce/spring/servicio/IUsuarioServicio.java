package com.my.ecommerce.spring.servicio;

import com.my.ecommerce.spring.entidades.Usuario;
import java.util.Optional;

/**
 *
 * @author Raúl Gómez
 */

public interface IUsuarioServicio {

   public Optional<Usuario> findById(Integer id);
   public Usuario save(Usuario usuario);
}
