package org.bentech.config;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver resolver =
            (HandlerExceptionResolver) new ApplicationContextProvider()
                    .applicationContext()
                    .getBean("handlerExceptionResolver");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            resolver.resolveException(
                    request,
                    response,
                    null,
                    new AuthenticationCredentialsNotFoundException("authentication required")
            );
            return;
        }
        filterChain.doFilter(request, response);
    }
}
