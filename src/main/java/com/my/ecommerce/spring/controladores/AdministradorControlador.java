package com.my.ecommerce.spring.controladores;

import com.my.ecommerce.spring.entidades.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.my.ecommerce.spring.servicio.IProductoServicio;

/**
 * esta clase esta bien
 * @author Raúl Gómez
 */

@Controller
@RequestMapping("/administrador")
public class AdministradorControlador {

    @Autowired
    private IProductoServicio productoServicio;
    
    @GetMapping("")
    public String home(Model model){
        
        List<Producto> productos = productoServicio.findAll();
        model.addAttribute("productos", productos);
        
        return "administrador/home";
    }
}
