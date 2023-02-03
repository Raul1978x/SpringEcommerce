package com.my.ecommerce.spring.controladores;

import com.my.ecommerce.spring.entidades.Usuario;
import com.my.ecommerce.spring.servicio.IUsuarioServicio;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
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
@RequestMapping("/usuario")
public class UsuarioControlador {

    private final Logger log = LoggerFactory.getLogger(UsuarioControlador.class);

    @Autowired
    private IUsuarioServicio usuarioServicio;

    @GetMapping("/registro")
    public String create() {
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario) {
        log.info("Usuario registro: {}", usuario);
        usuario.setTipo("USER");
        usuarioServicio.save(usuario);
        return "redirect:/";
    }

//     @PostMapping("/save")
//	public String save(Usuario usuario) {
//		log.info("Usuario registro: {}", usuario);
////		usuario.setTipo("USER");
////		usuario.setPassword( passEncode.encode(usuario.getPassword()));
////		usuarioService.save(usuario);		
//		return "redirect:/";
//	}
    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }

    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session){
        log.info("accesos: {}", usuario);
        Optional<Usuario> user= usuarioServicio.findByEmail(usuario.getEmail());
        log.info("usuario de bd: {}", user);
        if (user.isPresent()) {
            session.setAttribute("idusuario", user.get().getId());
            if (user.get().getTipo().equalsIgnoreCase("admin")) {
                return "redirect:/administrador";
            }
        }
        return "redirect:/";
    }
    
}
