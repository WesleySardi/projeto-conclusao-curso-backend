package org.example.biomedbacktdd.config;

import org.example.biomedbacktdd.security.jwt.JwtAuthFilter;
import org.example.biomedbacktdd.util.Paths;
import org.example.biomedbacktdd.util.Roles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
                .httpBasic(basic -> basic.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                Paths.DEP_ADMIN_PATH.getPath()+"/**",
                                Paths.RES_ADMIN_PATH.getPath()+"/**").hasRole(Roles.ADMIN.getRole())
                        .requestMatchers(
                                Paths.DEP_MANAGER_PATH.getPath()+"/**",
                                Paths.RES_MANAGER_PATH.getPath()+"/**").hasAnyRole(Roles.CUIDADOR.getRole(), Roles.ADMIN.getRole())
                        .requestMatchers(
                                Paths.DEP_COMMONUSER_PATH.getPath()+"/**",
                                Paths.RES_COMMONUSER_PATH.getPath()+"/**").hasAnyRole(Roles.RESPONSAVEL.getRole(), Roles.CUIDADOR.getRole(), Roles.ADMIN.getRole())
                        .requestMatchers(
                                "/api/responsible/findByTelefone/**",
                                "/api/url/*",
                                "/api/dependent/commonuser/findById/",
                                "/api/scanHistory/**",
                                "/api/responsible/findByTelefone/**",
                                "/api/url/encrypt",
                                "/api/url/decrypt",
                                "/api/notifications/sendAndStore",
                                "/api/dependent/findDependentNameByCpf/**",
                                "/api/email/**",
                                "/api/smshandler/**",
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
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> {
                });

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