package com.my.ecommerce.spring.controladores;

import com.my.ecommerce.spring.entidades.Producto;
import com.my.ecommerce.spring.servicio.ProductoServicio;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Raúl Gómez
 */
@Controller
@RequestMapping("/")
public class HomeControlador {

    private final Logger log = LoggerFactory.getLogger(HomeControlador.class);
            
    
    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("")
    public String home(Model model) {
        
        model.addAttribute("productos", productoServicio.findAll());

        return "usuario/home";
    }
    
    @GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		log.info("Id producto enviado como parámetro {}", id);
		Producto producto = new Producto();
		Optional<Producto> productoOptional = productoServicio.get(id);
		producto = productoOptional.get();

		model.addAttribute("producto", producto);

		return "usuario/productohome";
	}
}
