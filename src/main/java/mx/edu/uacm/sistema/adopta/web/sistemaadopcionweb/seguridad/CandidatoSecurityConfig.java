package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(2)
public class CandidatoSecurityConfig {



    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {

        http.antMatcher("/candidato/**")
                .authorizeRequests().anyRequest().hasAuthority("CANDIDATO")
                .and()
                .formLogin()
                .loginPage("/candidato/login")
                .usernameParameter("email")
                .loginProcessingUrl("/candidato/login")
                .defaultSuccessUrl("/candidato/home")
                .permitAll()
                .and()
                .logout().logoutUrl("/candidato/logout")
                .logoutSuccessUrl("/");
        return http.build();
    }
}
