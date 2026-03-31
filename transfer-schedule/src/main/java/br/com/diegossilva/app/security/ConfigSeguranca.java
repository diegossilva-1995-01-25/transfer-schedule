package br.com.diegossilva.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.diegossilva.app.service.DetalheCliente;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ConfigSeguranca {

    private final FiltroAuthJWT jwtAuthFilter;
    private final DetalheCliente detalheCliente;
    private final PasswordEncoder passwordEncoder;

    public ConfigSeguranca(FiltroAuthJWT jwtAuthFilter, DetalheCliente detalheCliente, PasswordEncoder passwordEncoder){
        this.jwtAuthFilter = jwtAuthFilter;
        this.detalheCliente = detalheCliente;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    static PasswordEncoder passwordEncoder(){
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() {
        return authentication -> {
            String username = authentication.getName();
            Object credentials = authentication.getCredentials();
            if (credentials == null) {
                throw new org.springframework.security.authentication.BadCredentialsException("No credentials provided");
            }
            String rawPassword = credentials.toString();

            var user = detalheCliente.loadUserByUsername(username);

            if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
                throw new org.springframework.security.authentication.BadCredentialsException("Invalid credentials");
            }

            return new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                    user, user.getPassword(), user.getAuthorities());
        };
    }

    @Bean
    SecurityFilterChain securityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(org.springframework.http.HttpMethod.POST, "/transf-sched/api/**").permitAll()
                .requestMatchers(org.springframework.http.HttpMethod.POST, "/transf-sched/api/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler securityContextLogoutHandler() {
        return new org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler();
    }
}