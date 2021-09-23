package org.bentech.service;

import org.bentech.dto.UserDto;
import org.bentech.entity.User;
import org.bentech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(User::toDto).collect(Collectors.toList());
    }

    public UserDto getByName(String name) {
        return userRepository.findByName(name).toDto();
    }
}