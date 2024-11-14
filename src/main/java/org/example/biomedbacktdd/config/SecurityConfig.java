package org.example.biomedbacktdd.config;

import org.example.biomedbacktdd.security.jwt.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desabilita CSRF para APIs REST
                .authorizeHttpRequests(auth -> auth
                        // Endpoints de autenticação estão liberados para todos
                        .requestMatchers(
                                "/api/dependent/admin/**",
                                "/api/mixed/admin/**",
                                "/api/responsible/admin/**").hasRole("ADMIN")
                        .requestMatchers(
                                "/api/dependent/manager/**",
                                "/api/mixed/manager/**",
                                "/api/responsible/manager/**").hasAnyRole("CUIDADOR", "ADMIN")
                        .requestMatchers(
                                "/api/dependent/commonuser/**",
                                "/api/mixed/commonuser/**",
                                "/api/responsible/commonuser/**").hasAnyRole("RESPONSÁVEL", "CUIDADOR", "ADMIN")
                        .requestMatchers(
                                "/api/email/**",
                                "/api/smshandler/**",
                                "/api/responsible/updatePassword",
                                "/api/responsible/findByTelefone/**",
                                "/api/responsible/findByEmail/**",
                                "/api/responsible/create/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v2/api-docs",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/v3/api-docs/**",
                                "/swagger-config"
                        ).permitAll()
                        .requestMatchers("/api/**").authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);  // Adiciona o filtro JWT antes da autenticação padrão

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}