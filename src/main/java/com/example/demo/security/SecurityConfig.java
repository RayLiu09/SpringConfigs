package com.example.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfig {
    @Value("${spring.default-password-encoder}")
    private String defaultPasswordEncoder;
    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("bcrypt", new BCryptPasswordEncoder(10, new SecureRandom()));
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder("secret", 16, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512));

        return new DelegatingPasswordEncoder(this.defaultPasswordEncoder, encoders);
    }
}
