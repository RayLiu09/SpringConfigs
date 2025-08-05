package com.example.demo.security;

import com.example.demo.security.filters.AuditLoggingFilter;
import com.example.demo.security.filters.RequestHeaderValidatorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(customAuthenticationProvider);
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        final String[] staticResources = new String[]{
                "/css/**",
                "/js/**",
                "/images/**",
                "/fonts/**",
                "/webjars/**",
                "/favicon.ico",
                "/error",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/v3/api-docs/**",
                "/actuator/**",
                "/docs/**",
                "/api-docs/**",
                "/.html",
                "/doc.html",
                "/druid/**"
        };
        http.httpBasic(Customizer.withDefaults()); // 默认的Http Basic认证方式
        // headers
        http.headers(c -> c.frameOptions(f -> f.sameOrigin()))
                .headers(h -> h.contentTypeOptions(c -> c.disable()));
        http.exceptionHandling(c -> c.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
        http.authorizeHttpRequests(c -> {
            c.requestMatchers(staticResources).permitAll();
            // 配置白名单endpoints
            c.requestMatchers("/auth/login", "/auth/logout", "/register").permitAll();
            c.requestMatchers("/api/**").authenticated();
        });
        http.csrf(c -> c.disable());
        http.logout(c -> c.logoutSuccessUrl("/logout").logoutSuccessHandler(new CustomLogoutSuccessHandler()));
        http.addFilterBefore(new RequestHeaderValidatorFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuditLoggingFilter(), BasicAuthenticationFilter.class).csrf().disable();
        return http.build();
    }
}
