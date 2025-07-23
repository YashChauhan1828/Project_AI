package com.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) // enables CORS using CorsConfigurationSource bean
            .csrf(AbstractHttpConfigurer::disable) // disables CSRF safely
            .authorizeHttpRequests(auth -> {
				try {
					auth
						.requestMatchers("/images/**").permitAll()
						.requestMatchers("/api/public/**").permitAll()
						.requestMatchers("/api/admin/**").permitAll()
						 .requestMatchers("/api/admin/**").hasRole("ADMIN")
					     .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
					     .anyRequest().permitAll()
						.and()
						.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
               
            );
        return http.build();
    }
}
