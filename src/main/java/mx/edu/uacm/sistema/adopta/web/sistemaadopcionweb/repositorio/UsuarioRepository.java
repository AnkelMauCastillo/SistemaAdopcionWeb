package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.repositorio;

import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query("select u from  Usuario u WHERE u.emailUsuario = ?1")
    public Usuario findByEmail(String email);
}
