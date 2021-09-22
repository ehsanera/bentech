package org.bentech.service;

import org.bentech.dto.UserDto;
import org.bentech.entity.User;
import org.bentech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(User::toDto).toList();
    }
}