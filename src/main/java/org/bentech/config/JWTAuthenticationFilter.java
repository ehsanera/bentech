package org.bentech.config;

import org.bentech.service.TokenAuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {
    private final HandlerExceptionResolver resolver =
            (HandlerExceptionResolver) new ApplicationContextProvider()
                    .applicationContext()
                    .getBean("handlerExceptionResolver");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication;
        try {
            authentication = new TokenAuthenticationService().getAuthentication((HttpServletRequest) request, (HttpServletResponse) response);
        } catch (Exception ex) {
            resolver.resolveException((HttpServletRequest) request, (HttpServletResponse) response, null, ex);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
