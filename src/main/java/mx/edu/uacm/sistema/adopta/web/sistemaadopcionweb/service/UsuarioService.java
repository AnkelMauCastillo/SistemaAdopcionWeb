package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.service;

import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Usuario;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UsuarioService {
    public static final int USUARIOS_POR_PAGINA = 4;

    @Autowired
    private UsuarioRepository usuarioRepository;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Usuario> listAll(){
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public Page<Usuario> listByPage(int pageNum, String sortField, String sortDir, String keyword){
        Sort sort = Sort.by(sortField);

        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, USUARIOS_POR_PAGINA, sort);

        if (keyword != null) {
            return usuarioRepository.findAll(keyword, pageable);
        }

        return usuarioRepository.findAll(pageable);
    }


    public Usuario guardar(Usuario usuario) {
        boolean estaActualizandoElUsuario = (usuario.getIdUsuario() != null);

        if (estaActualizandoElUsuario) {
            Usuario existeUsuario = usuarioRepository.findById(usuario.getIdUsuario()).get();
            if (usuario.getPassword().isEmpty()) {
                usuario.setPassword(existeUsuario.getPassword());
            }else {
                encodePassword(usuario);
            }
        } else {
            encodePassword(usuario);

        }
        return usuarioRepository.save(usuario);
    }

    private void encodePassword(Usuario usuario){
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);
    }

    public boolean isEmailUnique(Long id,String email){
        Usuario usuarioByEmail = usuarioRepository.getUsuarioByEmailUsuario(email);
        if (usuarioByEmail == null) return true;

        boolean estaCreandoNuevo = (id == null);

        if (estaCreandoNuevo) {
            if (usuarioByEmail != null) return false;
        }else {
            if (usuarioByEmail.getIdUsuario() != id){
                return false;
            }
        }
        return true;
    }

    public Usuario get(Long id) throws UserNotFoundException {
        try {
            return usuarioRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new UserNotFoundException("No se pudo encontrar ningún usuario con ID: " + id);
        }
    }

    public void delete(Long id) throws UserNotFoundException {
        Long countById = usuarioRepository.countByIdUsuario(id);
        if (countById == null || countById == 0) {
            throw new UserNotFoundException("No se pudo encontrar ningún usuario con ID: " + id);
        }
        usuarioRepository.deleteById(id);

    }

    public void updateUsuarioEnabledStatus(Long id, boolean habilitado){
        usuarioRepository.updateEnableHabilidato(id, habilitado);
    }
}
