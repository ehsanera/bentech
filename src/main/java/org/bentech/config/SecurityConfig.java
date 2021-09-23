package org.bentech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return new authenticationManager();
    }

    public void configure(HttpSecurity http) throws Exception {
        // security just for apis with "/api/**" pattern. security filters ignore other apis (like swagger or frontend urls)
        http.antMatcher("/api/**");
        http.csrf().disable().cors().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new AuthFilter(), FilterSecurityInterceptor.class)
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/auth/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/user/phone/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/user/**").hasRole("SUPER_ADMIN")
                .antMatchers("/api/widget/data/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/dlookup/**").authenticated()
                .antMatchers("/api/dlookup/**").hasRole("SUPER_ADMIN")
                .antMatchers("/api/temporary/**").hasRole("SUPER_ADMIN")
                .antMatchers(HttpMethod.POST, "/api/whiteBrand").hasRole("SUPER_ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/whiteBrand").hasRole("SUPER_ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/whiteBrand/**").hasRole("SUPER_ADMIN")
                .antMatchers(HttpMethod.GET, "/api/bot/**").hasRole("SUPER_ADMIN")
                .antMatchers("/api/superAdmin/**").hasRole("SUPER_ADMIN")
                .antMatchers("/api/whiteList/v1/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/plan/**").permitAll()
                .antMatchers("/api/plan/**").hasRole("SUPER_ADMIN")
                .antMatchers("/api/broadcastMessage/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                .antMatchers("/api/whiteBrand/plus").hasAnyRole("ADMIN", "SUPER_ADMIN")
                .antMatchers("/api/payment/verify").permitAll()
                .antMatchers("/api/payment/**").authenticated()
                .antMatchers("/api/authBot/**").hasRole("SUPER_ADMIN")
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
    }

    @Override
     public void configure(WebSecurity web ) {
        web.ignoring().antMatchers("/api/auth/login");
        web.ignoring().antMatchers("/api/auth/register");
        web.ignoring().antMatchers("/api/auth/join");
        web.ignoring().antMatchers("/api/endpoint/**");
        web.ignoring().antMatchers("/api/widget/**");
        web.ignoring().antMatchers("/api/whiteBrand/getByName");
        web.ignoring().antMatchers("/api/whiteList/v1/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/api/plan");
        web.ignoring().antMatchers("/api/payment/verify");
    }
}
