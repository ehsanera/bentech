package org.bentech.controller;

import org.bentech.dto.user.UserCreateDto;
import org.bentech.dto.user.UserDto;
import org.bentech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("api/user")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("api/user/nameExists")
    public ResponseEntity userNameExists(@RequestParam @Min(3) String userName) {
        Boolean exists = userService.userNameExists(userName);
        if (exists) {
            return new ResponseEntity<Error>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Error>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("api/user")
    public ResponseEntity<?> save(@RequestBody UserCreateDto userCreateDto) {
        if (userService.userNameExists(userCreateDto.userName)) {
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        } else {
            return ResponseEntity.ok(userService.save(userCreateDto));
        }
    }
}