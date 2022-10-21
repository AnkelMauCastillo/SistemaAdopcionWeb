package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.repositorio;

import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("select u from  Usuario u WHERE u.emailUsuario = ?1")
    Usuario findByEmail(String email);
}
