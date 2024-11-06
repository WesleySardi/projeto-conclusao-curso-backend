package org.example.biomedbacktdd.config;

import org.example.biomedbacktdd.security.jwt.JwtTokenFilter;
import org.example.biomedbacktdd.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtTokenFilter customFilter = new JwtTokenFilter(tokenProvider);

        return http
                .httpBasic(basic -> basic.disable())
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers(
                                        "/api/dependent/admin/**",
                                        "/api/mixed/admin/**",
                                        "/api/email/admin/**",
                                        "/api/responsible/admin/**").hasRole("ADMIN")
                                .requestMatchers(
                                        "/api/dependent/manager/**",
                                        "/api/mixed/manager/**",
                                        "/api/email/manager/**",
                                        "/api/responsible/manager/**").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers(
                                        "/api/dependent/commonuser/**",
                                        "/api/mixed/commonuser/**",
                                        "/api/email/commonuser/**",
                                        "/api/responsible/commonuser/**").hasAnyRole("COMMON_USER", "MANAGER", "ADMIN")
                                .requestMatchers(
                                        "/auth/signin",
                                        "/auth/register",
                                        "/auth/refreshToken/**",
                                        "/api/email/sendQrCode",
                                        "/api/responsible/updatePassword",
                                        "/api/responsible/findByTelefone/**",
                                        "/api/responsible/findByEmail/**",
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
                .cors(cors -> {
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        encoders.put("pbkdf2", pbkdf2Encoder);

        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);

        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

        return passwordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}