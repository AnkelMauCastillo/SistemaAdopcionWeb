package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb;

import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Mascota;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Role;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Usuario;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.repositorio.MascotaRepository;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.repositorio.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private MascotaRepository mascotaRepository;

    // test methods go below
    @Test
    public void testCreateUser() {
        Usuario user = new Usuario();
        user.setEmailUsuario("eduardin@uacm.edu.mx");
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
        String email = "vegeta@uacm.edu.mx";
        Usuario usuario = repo.findByEmail(email);

        assertThat(usuario).isNotNull();
    }

    @Test
    public void testListaDeUsuarios(){
        Iterable<Usuario> listaUsuarios = repo.findAll();
        listaUsuarios.forEach(usuario -> System.out.println(usuario.toString()));
    }

    @Test
    public void testGetUsuariobyId(){
        Usuario usuario = repo.findById(3L).get();
        System.out.println(usuario);
        assertThat(usuario).isNotNull();
    }

    @Test
    public void testUpdateUsuarioDetails(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Usuario usuario = repo.findById(2L).get();
        usuario.setPassword(passwordEncoder.encode("123456"));
        System.out.println(usuario);

        repo.save(usuario);
    }

    @Test
    public void testDeleteUsuario(){
        Long usuarioId = 10L;
        repo.deleteById(usuarioId);

    }

    @Test
    public void tesTidMascota(){
        Mascota mascota = mascotaRepository.findByIdMascota(1L);
        System.out.println(mascota);

    }



}
