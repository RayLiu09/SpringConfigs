package com.example.demo.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * Called when an unauthenticated user tries to access a secured resource.
     *
     * @param request       that resulted in an <code>AuthenticationException</code>
     * @param response      so that the user agent can begin authentication
     * @param authException that caused the invocation
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
       response.addHeader("X-Failed-At", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
       response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
