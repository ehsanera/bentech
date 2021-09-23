package org.bentech.dto;

import org.bentech.enums.Roles;

import java.util.List;

public class UserDto {
    public String name;
    public String pass;
    public List<Roles> roles;

    public UserDto(String name, String pass, List<Roles> roles) {
        this.name = name;
        this.pass = pass;
        this.roles = roles;
    }
}
