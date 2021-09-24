package org.bentech.service;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bentech.config.ApplicationContextProvider;
import org.bentech.dto.user.CustomUser;
import org.bentech.dto.user.UserDto;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TokenAuthenticationService {
    private final Long EXPIRATION = 864000000L;
    private final String SECRET = "ThisIsASecret@Ehsan";
    private final String TOKEN_PREFIX = "Bearer";
    private final String HEADER_STRING = "Authorization";

    private final UserService userService = (UserService) new ApplicationContextProvider().applicationContext().getBean("authRepository");

    public void addAuthentication(HttpServletResponse res, Authentication principal) {
        UserDto userProfile = ((CustomUser) principal.getPrincipal()).profile;
        String jwt = Jwts.builder()
                .setSubject(new Gson().toJson(userProfile))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, "$TOKEN_PREFIX $jwt");
    }

    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null && !token.isBlank()) {
            return getAuthentication(token, response);
        } else {
            throw new AuthenticationCredentialsNotFoundException("token is null or empty");
        }
    }

    private Authentication getAuthentication(String token, HttpServletResponse response) {
        UserDto profile = new Gson().fromJson(
                Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject(),
                UserDto.class
        );
        if (profile != null) {
            UserDto dto = userService.getByUserName(profile.userName);
            if (dto != null) {
                List<SimpleGrantedAuthority> roles = List.of(new SimpleGrantedAuthority("ROLE" + dto.roles));
                return new UsernamePasswordAuthenticationToken(
                        new CustomUser(
                                dto.userName,
                                dto.pass,
                                roles
                        ), null, roles
                );
            } else {
                throw new AuthenticationCredentialsNotFoundException("user not found!");
            }
        } else {
            throw new AuthenticationCredentialsNotFoundException("JWT not found!");
        }
    }
}
