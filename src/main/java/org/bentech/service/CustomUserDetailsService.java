package org.bentech.service;

import org.bentech.dto.UserDto;
import org.bentech.entity.User;
import org.bentech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userService.getByName(username);
        return new UserDetailsAdapter(user);
    }

    private static class UserDetailsAdapter extends UserDto implements UserDetails {
        UserDetailsAdapter(UserDto user) {
            super(user);
        }
    }
}