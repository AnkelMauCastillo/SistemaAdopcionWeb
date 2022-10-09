package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.controlador;

import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Usuario;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioControlador {

    @Autowired
    private UsuarioRepository userRepo;

    @GetMapping("")
    public String paginaDeInicio(){
        return "index";
    }

}
