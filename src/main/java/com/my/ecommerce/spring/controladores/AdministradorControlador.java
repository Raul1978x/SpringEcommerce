package com.my.ecommerce.spring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Raúl Gómez
 */

@Controller
@RequestMapping("/administrador")
public class AdministradorControlador {

    @GetMapping("")
    public String home(){
        return "administrador/home";
    }
}
