package com.hortifood.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/auth/**",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/validarLoginCliente",
                    "/validaCampoCliente",
                    "/api/clientAuthcontroller/validarLoginCliente",
                    "/api/clientAuthcontroller/validaCampoCliente",
                    "/api/clientcontroller/criarcliente",
                    "/api/entregadorAuthcontroller/validarLoginEntregador",
                    "/api/entregadorAuthcontroller/validarCamposEntregador",
                    "/api/lojacontroller/criarLoja",
                    "/api/lojacontroller/alterarLoja",
                    "/api/lojacontroller/adicionarItemCardapio/{lojaId}",
                    "/api/lojacontroller/deletarLoja",
                    "/api/lojacontroller/acharLoja",
                    "/api/lojaAuthcontroller/validarCampoLoja",
                    "/api/lojaAuthcontroller/validarLoginLoja"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}

