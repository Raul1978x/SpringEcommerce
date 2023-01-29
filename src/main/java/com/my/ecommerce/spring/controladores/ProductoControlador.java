package com.my.ecommerce.spring.controladores;

import com.my.ecommerce.spring.entidades.Producto;
import com.my.ecommerce.spring.entidades.Usuario;
import com.my.ecommerce.spring.servicio.ProductoServicio;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Raúl Gómez
 */

@Controller
@RequestMapping("/productos")
public class ProductoControlador {
    
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    private ProductoServicio productoServicio;
            
    
    @GetMapping("")
    public String show(){
        return "productos/show";
    }
    
    @GetMapping("/crear")
    public String crear(){
        return "productos/create.html";
    }
    
    @PostMapping("/guardar")
    public String guardar(Producto producto){
        LOGGER.info("Este es el objeto producto {}", producto);
        Usuario usuario = new Usuario(1, "", "", "", "", "", "", "", null, null);
        producto.setUsuario(usuario);
        productoServicio.save(producto);
        return "redirect:/productos";
    }
}
