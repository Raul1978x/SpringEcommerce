package com.my.ecommerce.spring.controladores;

import com.my.ecommerce.spring.entidades.DetalleOrden;
import com.my.ecommerce.spring.entidades.Orden;
import com.my.ecommerce.spring.entidades.Producto;
import com.my.ecommerce.spring.entidades.Usuario;
import com.my.ecommerce.spring.servicio.IDetalleOrdenServicio;
import com.my.ecommerce.spring.servicio.IOrdenServicio;
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
import com.my.ecommerce.spring.servicio.IProductoServicio;
import com.my.ecommerce.spring.servicio.IUsuarioServicio;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.stream.Collectors;

/**
 *
 * @author Raúl Gómez
 */
@Controller
@RequestMapping("/")
public class HomeControlador {

    private final Logger log = LoggerFactory.getLogger(HomeControlador.class);

    @Autowired
    private IProductoServicio productoServicio;

    @Autowired
    private IUsuarioServicio usuarioServicio;

    @Autowired
    private IOrdenServicio ordenServicio;

    @Autowired
    private IDetalleOrdenServicio detalleOrdenServicio;

    //para almacenar los detalles de la orden
    List<DetalleOrden> detalles = new ArrayList<>();
    //datos de la orden     
    Orden orden = new Orden();

    @GetMapping("")
    public String home(Model model, HttpSession session) {

//        log.info("Sesion del usuario: {}", session.getAttribute("idusuario"));
//        model.addAttribute("productos", productoServicio.findAll());
        log.info("Sesion del usuario: {}", session.getAttribute("idusuario"));
        model.addAttribute("productos", productoServicio.findAll());
        //session
        model.addAttribute("sesion", session.getAttribute("idusuario"));

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
    public String getCart(Model model, HttpSession session) {
        sumaTotal(0, model);

        //sesion
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "/usuario/carrito";
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session) {
        Usuario usuario = usuarioServicio.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        model.addAttribute("usuario", usuario);
        sumaTotal(0, model);

        return "usuario/resumenorden";
    }

    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session) {
        orden.setFechaCreacion(new Date());
        orden.setNumero(ordenServicio.generarNumeroOrden());

        Usuario usuario = usuarioServicio.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        orden.setUsuario(usuario);
        ordenServicio.save(orden);

        for (DetalleOrden dt : detalles) {
            dt.setOrden(orden);
            detalleOrdenServicio.save(dt);
        }

        orden = new Orden();
        detalles.clear();

        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model) {
        log.info("nombre del producto: " + nombre);
        List<Producto> productos = productoServicio.findAll().stream().filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList());

        model.addAttribute("productos", productos);
        return "usuario/home";
    }

    public void sumaTotal(double sumaTotal, Model model) {
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

    }

}
