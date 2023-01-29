package com.my.ecommerce.spring.controladores;

import com.my.ecommerce.spring.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Raúl Gómez
 */
@Controller
@RequestMapping("/")
public class HomeControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("")
    public String home(Model model) {
        
        model.addAttribute("productos", productoServicio.findAll());

        return "usuario/home";
    }
}
