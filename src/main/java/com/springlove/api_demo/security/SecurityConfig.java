package com.springlove.api_demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(config ->
                    config
                            // For Products
                            .requestMatchers(HttpMethod.GET, "/api/products").hasAnyRole("USER", "MANAGER")
                            .requestMatchers(HttpMethod.GET, "/api/products/**").hasAnyRole("USER", "MANAGER")
                            .requestMatchers(HttpMethod.POST, "/api/products").hasAnyRole("MANAGER")
                            .requestMatchers(HttpMethod.PUT, "/api/products/**").hasAnyRole("MANAGER")
                            .requestMatchers(HttpMethod.PATCH, "/api/products/**").hasAnyRole("MANAGER")
                            .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasAnyRole("MANAGER")
                            // For Orders
                            .requestMatchers(HttpMethod.GET, "/api/orders").hasAnyRole("MANAGER")
                            .requestMatchers(HttpMethod.GET, "/api/orders/**").hasAnyRole("MANAGER")
                            .requestMatchers(HttpMethod.POST, "/api/orders").hasAnyRole("USER", "MANAGER")
                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasRole("MANAGER")

        );

        // Enable httpBasic because the app need username + password
        http.httpBasic(Customizer.withDefaults());

        // Disable csrf
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
