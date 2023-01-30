package com.my.ecommerce.spring.servicio;

import com.my.ecommerce.spring.entidades.Usuario;
import com.my.ecommerce.spring.repositorios.UsuarioRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Raúl Gómez
 */
@Service
public class UsuarioServicioImpl implements UsuarioServicio{

    @Autowired
    private UsuarioRepositorio UsuarioRepositorio;

    @Override
    public Optional<Usuario> findById(Integer id) {
        return UsuarioRepositorio.findById(id);
    }
    

   
}
