package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.controlador;

import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Role;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Usuario;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

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
        List<String> options = new ArrayList<String>();
        model.addAttribute("usuario", new Usuario());

        for(Role role: Role.values()){
            options.add(role.toString());
        }
        model.addAttribute("options", options);

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

    @GetMapping("/donador/login")
    public String verPaginaDonadorLogin(){
        return "donador/donador_login";
    }

    @GetMapping("/candidato/login")
    public String verPaginaCandidatoLogin(){
        return "candidato/candidato_login";
    }

    @GetMapping("/donador/home")
    public String verPaginaDonadorHome(){
        return "donador/donador_home";
    }

    @GetMapping("/candidato/home")
    public String verPaginaCandidatoHome(){
        return "candidato/candidato_home";
    }




}
