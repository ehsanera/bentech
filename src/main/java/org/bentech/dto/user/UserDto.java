package org.bentech.dto.user;

import org.bentech.enums.Roles;

public class UserDto {
    public String userName;
    public String pass;
    public Roles roles;

    public UserDto(String userName, String pass, Roles roles) {
        this.userName = userName;
        this.pass = pass;
        this.roles = roles;
    }
}
