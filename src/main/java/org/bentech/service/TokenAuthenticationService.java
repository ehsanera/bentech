package org.bentech.service;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bentech.config.ApplicationContextProvider;
import org.bentech.dto.CustomUser;
import org.bentech.dto.UserDto;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.*;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenAuthenticationService {
    private Long EXPIRATION  = 0L;
    private String SECRET = "";
    private const String TOKEN_PREFIX = "Bearer";
    private const String HEADER_STRING = "Authorization";

    private UserService userService =(UserService)  new ApplicationContextProvider().applicationContext().getBean("authRepository");

    public void addAuthentication(HttpServletResponse res , Authentication principal ) {
        CustomUser userProfile = ((CustomUser) principal.getPrincipal()).profile;
        String jwt = Jwts.builder()
                .setSubject(new Gson().toJson(userProfile))
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .signWith(SignatureAlgorithm.HS512, "ThisIsASecret@Ehsan")
                .compact();
        res.addHeader("Authorization", "$TOKEN_PREFIX $jwt");
    }

    public Authentication getAuthentication(HttpServletRequest request , HttpServletResponse response ) {
        String token = request.getHeader("Authorization");
        if (token != null && !token.isBlank()) {
            return getAuthentication(token, response);
        } else {
            throw new AuthenticationCredentialsNotFoundException("token is null or empty");
        }
    }

    private Authentication getAuthentication(String token ,HttpServletResponse response ) {
        String profile = new Gson().fromJson(
                Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).body.subject,
                UserDto.class
        )
        if (profile != null) {
            val dto = loadUserById(profile.userId)
            return if (dto != null) {
                val userRoles = dto.roles.map { SimpleGrantedAuthority("ROLE_$it") }.toMutableList()
                UsernamePasswordAuthenticationToken(
                        CustomUser(
                                dto,
                                dto.userId,
                                dto.transactionHash,
                                userRoles
                        ), null, userRoles
                )
            } else {
                Logger.getGlobal().info(profile.toString())
                Logger.getGlobal().info(TenantContext.getCurrentTenant().toString())
                throw AuthenticationCredentialsNotFoundException("user not found!")
            }
        } else {
            throw AuthenticationCredentialsNotFoundException("JWT not found!")
        }
    }

    private fun loadUserById(id: Int): AuthDto? {
        val user = authRepository.findByIdOrNull(id)?.toDto()
        user?.let {
            return user
        }
        return null
    }
}
