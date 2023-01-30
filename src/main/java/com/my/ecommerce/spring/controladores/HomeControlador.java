package com.my.ecommerce.spring.controladores;

import com.my.ecommerce.spring.entidades.DetalleOrden;
import com.my.ecommerce.spring.entidades.Orden;
import com.my.ecommerce.spring.entidades.Producto;
import com.my.ecommerce.spring.servicio.ProductoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    //para almacenar los detalles de la orden
    List<DetalleOrden> detalles = new ArrayList<>();
    //datos de la orden     
    Orden orden = new Orden();

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

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;

        Optional<Producto> optionalProducto = productoServicio.get(id);
        producto = optionalProducto.get();
//		log.info("Producto añadido: {}", producto);
//		log.info("Cantidad: {}", cantidad);

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);

        //validar que le producto no se añada 2 veces
        Integer idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

        if (!ingresado) {
            detalles.add(detalleOrden);
        }
        sumaTotal(sumaTotal, model);
        return "usuario/carrito";
    }

    @GetMapping("delete/cart/{id}")
    public String deleteProductCart(@PathVariable Integer id, Model model) {
        List<DetalleOrden> ordenNueva = new ArrayList<>();

        for (DetalleOrden detalleOrden : detalles) {
            if (detalleOrden.getProducto().getId() != id) {
                ordenNueva.add(detalleOrden);

            }
        }
        detalles = ordenNueva;

        double sumaTotal = 0;
        sumaTotal(sumaTotal, model);
        return "usuario/carrito";
    }
    
    @GetMapping("/getCart")
    public String getCart(Model model){
        sumaTotal(0, model);
        return "/usuario/carrito";
    }

    public void sumaTotal(double sumaTotal, Model model) {
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

    }

}
