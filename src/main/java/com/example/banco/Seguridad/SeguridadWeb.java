package com.example.banco.Seguridad;


import com.example.banco.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SeguridadWeb {

    @Autowired
    public UsuarioServicio usuarioServicio;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usuarioServicio)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/admin/*").hasRole("ADMIN")
                                .requestMatchers("/css/**", "/js/**", "/img/**",
                                        "/registro", "/registrar").permitAll()  // Permite acceso a recursos estáticos
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")  // Página de inicio de sesión personalizada
                                .loginProcessingUrl("/login")  // URL de procesamiento del inicio de sesión
                                .usernameParameter("email")  // Parámetro para el nombre de usuario
                                .passwordParameter("password")  // Parámetro para la contraseña
                                .defaultSuccessUrl("/inicio")  // URL de éxito después del inicio de sesión
                                .permitAll()  // Permite acceso a la página de inicio de sesión y otras URLs sin autenticación
                )
                .logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .permitAll() // Permite acceso a la funcionalidad de cierre de sesión sin autenticación
                );
        return http.build();
    }

}
