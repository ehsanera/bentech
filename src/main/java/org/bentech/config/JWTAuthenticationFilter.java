package org.bentech.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter {
    private HandlerExceptionResolver resolver =
            (HandlerExceptionResolver) new ApplicationContextProvider()
                    .applicationContext()
                    .getBean("handlerExceptionResolver");

    @Override
    public void doFilter(ServletRequest request , ServletResponse response , FilterChain filterChain ) {
        HttpServletResponse authentication=null;
        try {
            TokenAuthenticationService().getAuthentication((HttpServletRequest) request, (HttpServletResponse) response);

        }catch (Exception ex){
            resolver.resolveException((HttpServletRequest) request , ( HttpServletResponse) response, null, ex);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication() = authentication;
        filterChain.doFilter(request, response);
    }
}
