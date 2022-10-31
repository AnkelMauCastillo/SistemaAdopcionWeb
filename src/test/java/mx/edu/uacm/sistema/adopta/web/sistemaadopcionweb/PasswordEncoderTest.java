package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordEncoderTest {
    @Test
    public void testEncodePassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        String encodePassword = passwordEncoder.encode(rawPassword);

        System.out.println(encodePassword);
        boolean matches = passwordEncoder.matches(rawPassword, encodePassword);

        assertThat(matches).isTrue();
    }
}
