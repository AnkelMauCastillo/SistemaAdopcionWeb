package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.seguridad;

import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(1)
public class AdminSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll();
        http.antMatcher("/donador/**")
                .authorizeRequests().anyRequest().hasAuthority("DONADOR")
                .and()
                .formLogin()
                .loginPage("/donador/login")
                .usernameParameter("email")
                .loginProcessingUrl("/donador/login")
                .defaultSuccessUrl("/donador/home")
                .permitAll()
                .and()
                .logout().logoutUrl("/donador/logout")
                .logoutSuccessUrl("/");
        return http.build();
    }
}
