package org.bentech.service;

import org.bentech.dto.CustomUser;
import org.bentech.dto.UserDto;
import org.bentech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userRepository.findByUserName(username).toDto();
        if (user != null) {
            List<SimpleGrantedAuthority> roles = user.roles.stream().map(role ->
                    new SimpleGrantedAuthority("ROLE" + role)
            ).toList();
            return new CustomUser(user.name, user.pass, roles);
        } else {
            return null;
        }
    }
}