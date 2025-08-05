package com.example.demo.security.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class AuditLoggingFilter implements Filter {
    /**
     * Add audit logging functionality after the request is processed
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;
        log.info("Audit Logging: {} {}", request.getMethod(), request.getRequestURI());
        if (Objects.equals(request.getMethod(), HttpMethod.POST.name())) {
            log.info("Request Body: {}", request.getReader().lines().collect(java.util.stream.Collectors.joining()));
        }
        filterChain.doFilter(request, response);
    }
}
