package com.edu.shop.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.HttpMethodConstraintElement;
import org.mapstruct.BeanMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfig corsConfig) throws Exception {
        //DE MAS ESPECIFICO A GENERAL
        http
                .cors(cors->cors.disable())
                .cors(cors->cors.configurationSource(corsConfig.corsConfigurationSource()))//TODO VERIFICAR QUITANDO ESTE
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(authorize-> authorize
                        .requestMatchers("/api/client/**").hasAnyRole("ADMIN","CUSTOMER")
                        .requestMatchers("/api/category/**").hasAnyRole("ADMIN")
                        .requestMatchers("/api/shopping/add").hasAuthority("add_shopping")
                        .requestMatchers("/api/shopping/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
