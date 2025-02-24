package com.edu.shop.config;


import com.edu.shop.exception.SecurityExceptionHandlerFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final SecurityExceptionHandlerFilter securityExceptionHandlerFilter;


    public SecurityConfig(JwtFilter jwtFilter, SecurityExceptionHandlerFilter securityExceptionHandlerFilter) {
        this.jwtFilter = jwtFilter;
        this.securityExceptionHandlerFilter = securityExceptionHandlerFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfig corsConfig) throws Exception {
        http
                .cors(cors->cors.disable())
                .cors(cors->cors.configurationSource(corsConfig.corsConfigurationSource()))
                .csrf(csrf->csrf.disable())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize-> authorize
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/client/**").hasAnyRole("ADMIN","CUSTOMER")
                        .requestMatchers("/api/category/**").hasAnyRole("ADMIN")
                        .requestMatchers("/api/shopping/add").hasAuthority("add_shopping")
                        .requestMatchers("/api/shopping/**").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter .class)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(securityExceptionHandlerFilter)
                        .accessDeniedHandler(securityExceptionHandlerFilter)
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
