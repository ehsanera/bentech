package org.bentech.dto;

import org.bentech.enums.Roles;

public class UserDto {
    Long id;
    String name;
    Roles roles;

    public UserDto(Long id, String name, Roles roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }
}
