package com.my.ecommerce.spring.servicio;

import com.my.ecommerce.spring.entidades.Usuario;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.my.ecommerce.spring.repositorios.IUsuarioRepositorio;

/**
 *
 * @author Raúl Gómez
 */
@Service
public class UsuarioServicioImpl implements IUsuarioServicio {

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepositorio.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

}
