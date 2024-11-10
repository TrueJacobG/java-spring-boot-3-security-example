package com.github.truejacobg.java_spring_boot_3_security_example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Configuration
public class AuthenticationManagerConfig {

    @Bean
    public AuthenticationManager authenticationManager() {
        return authentication -> new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
            authentication.getCredentials(), authentication.getAuthorities());
    }
}
