package org.bentech.service;

import org.bentech.dto.user.UserCreateDto;
import org.bentech.dto.user.UserDto;
import org.bentech.dto.user.UserUpdateDto;
import org.bentech.entity.UserEntity;
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
        return userRepository.findAll().stream().map(UserEntity::toDto).collect(Collectors.toList());
    }

    public Boolean userNameExists(String userName) {
        return userRepository.existsByUserName(userName);
    }

    public UserDto getByUserName(String userName) {
        return userRepository.findByUserName(userName).toDto();
    }

    public UserDto save(UserCreateDto userDto) {
        return userRepository.save(userDto.to()).toDto();
    }

    public UserDto update(UserUpdateDto userDto) {
        UserDto user = getByUserName(userDto.userName);
        if (user == null) {
            return null;
        } else {
            return userRepository.save(user.to(user)).toDto();
        }
    }
}