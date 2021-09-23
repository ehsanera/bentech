package org.bentech.service;

import org.bentech.dto.user.UserCreateDto;
import org.bentech.dto.user.UserDto;
import org.bentech.entity.UserEntity;
import org.bentech.entity.UserRolesEntity;
import org.bentech.repository.RolesRepository;
import org.bentech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RolesRepository rolesRepository;

    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserEntity::toDto).collect(Collectors.toList());
    }

    public UserDto getByUserName(String userName) {
        return userRepository.findByUserName(userName).toDto();
    }

    public UserDto save(UserCreateDto userDto) {
        Collection<UserRolesEntity> roles = userDto.roles.stream().map(it -> rolesRepository.getById(it)).toList();
        return userRepository.save(userDto.to(roles)).toDto();
    }
}