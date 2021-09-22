package org.bentech.controller;

import org.bentech.annotation.ApiMapping;
import org.bentech.dto.UserDto;
import org.bentech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("api/user")
    public List<UserDto> getAll() {
        return userService.getAll();
    }
}
