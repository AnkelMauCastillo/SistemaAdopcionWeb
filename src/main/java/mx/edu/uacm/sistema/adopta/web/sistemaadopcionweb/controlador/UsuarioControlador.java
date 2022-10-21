package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.controlador;

import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Usuario;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioControlador {

    @Autowired
    private UsuarioRepository userRepo;

    @GetMapping("")
    public String paginaDeInicio(){
        return "index";
    }


    @GetMapping("/registro")
    public String mostrarFormularioDeRegistro(Model model){
        model.addAttribute("usuario", new Usuario());

        return "signup_form";

    }

    @PostMapping("/registro_de_proceso")
    public String processRegister(Usuario user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }

}
