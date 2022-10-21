package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.seguridad;

import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Usuario;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = userRepo.findByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("User no found");
        }
        return  new CustomUserDetails(usuario);
    }
}
