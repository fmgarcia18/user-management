package com.fmgarcia.user.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/h2-console/**").permitAll() // ⬅️ Permite el acceso a la consola H2
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // ⬅️ Deshabilita CSRF para la consola H2
            .headers(headers -> headers.frameOptions().disable()); // ⬅️ Permite que la página se cargue en un iframe
        
        return http.build();
    }
}
