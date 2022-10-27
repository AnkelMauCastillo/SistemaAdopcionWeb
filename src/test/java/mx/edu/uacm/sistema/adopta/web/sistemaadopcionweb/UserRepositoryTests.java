package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb;

import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Role;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Usuario;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.repositorio.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository repo;

    // test methods go below
    @Test
    public void testCreateUser() {
        Usuario user = new Usuario();
        user.setEmailUsuario("eduardo@uacm.edu.mx");
        user.setPassword("123456");
        user.setNombreUsuario("EDuardo");
        //user.setIdRolUsuario(1);
        user.setRole(Role.DONADOR);


        Usuario savedUser = repo.save(user);

        Usuario existUser = entityManager.find(Usuario.class, savedUser.getIdUsuario());

        assertThat(user.getEmailUsuario()).isEqualTo(existUser.getEmailUsuario());

    }

    @Test
    public void testFindByEmail(){
        String email = "angel@uacm.edu.mx";
        //Usuario usuario = repo.findByEmail(email);

        //assertThat(usuario).isNotNull();
    }

}
