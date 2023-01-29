package com.my.ecommerce.spring.controladores;

import com.my.ecommerce.spring.entidades.Producto;
import com.my.ecommerce.spring.entidades.Usuario;
import com.my.ecommerce.spring.servicio.ProductoServicio;
import com.my.ecommerce.spring.servicio.UploadFileServicio;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private UploadFileServicio upload;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("productos", productoServicio.findAll());
        return "productos/show.html";
    }

    @GetMapping("/crear")
    public String crear() {
        return "productos/create.html";
    }

    @PostMapping("/guardar")
    public String guardar(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        LOGGER.info("Este es el objeto producto {}", producto);
        Usuario usuario = new Usuario(1, "", "", "", "", "", "", "", null, null);
        producto.setUsuario(usuario);
//  imagen  
        if (producto.getId() == null) { //cuando se crea un producto
            String nombreImagen = upload.guardarImage(file);
            producto.setImagen(nombreImagen);
        } else {

        }
        productoServicio.save(producto);
        return "redirect:/productos";
    }
//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable Integer id){
//        Producto producto = new Producto();
//        Optional<Producto> optionalProducto = productoServicio.get(id);
//        producto = optionalProducto.get();
//        LOGGER.info("producto buscado {}", producto);
//        return "/productos/edit.html";
//    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoServicio.get(id);
        producto = optionalProducto.get();

        LOGGER.info("Producto buscado: {}", producto);
        model.addAttribute("producto", producto);

        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        
            Producto p = new Producto();
            p = productoServicio.get(producto.getId()).get();
        
        if (file.isEmpty()) { // editamos el producto pero no cambiamos la imagen
            producto.setImagen(p.getImagen());

        } else {// cuando se edita tambien la imagem 
            //eliminar cuando no sea la imagen por defecto
            if (!p.getImagen().equals("default.jpg")) {
                upload.deleteImage(p.getImagen());
            }
            String nombreImagen = upload.guardarImage(file);
            producto.setImagen(nombreImagen);

        }
        producto.setUsuario(p.getUsuario());
        productoServicio.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Producto p = new Producto();
        p = productoServicio.get(id).get();
        //eliminar cuando no sea la imagen por defecto
        if (!p.getImagen().equals("default.jpg")) {
            upload.deleteImage(p.getImagen());
        }
        productoServicio.delete(id);
        return "redirect:/productos";
    }

}
