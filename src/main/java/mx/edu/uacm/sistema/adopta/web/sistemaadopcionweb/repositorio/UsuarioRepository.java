package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.repositorio;

import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

    @Query("select u from  Usuario u WHERE u.emailUsuario = ?1")
    public Usuario findByEmail(String email);

    @Query("SELECT u from Usuario u WHERE u.emailUsuario = :email")
    public Usuario getUsuarioByEmailUsuario(@Param("email") String email);

    public Long countByIdUsuario(Long idUsuario);

    @Query("select u from Usuario u WHERE CONCAT(u.idUsuario, ' ', u.emailUsuario, ' ', u.nombreUsuario, ' ', u.apellidoPaterno, ' ', u.apellidoMaterno) like %?1%")
    public Page<Usuario> findAll(String Keyword, Pageable pageable);

    @Query("UPDATE Usuario u SET u.habilitado = ?2 where u.idUsuario = ?1")
    @Modifying
    public void updateEnableHabilidato(Long id, boolean habilitado);
}
