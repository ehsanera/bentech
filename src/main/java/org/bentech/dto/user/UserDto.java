package org.bentech.dto.user;

import org.bentech.entity.UserRolesEntity;

import java.util.Collection;

public class UserDto {
    public String userName;
    public String pass;
    public Collection<UserRolesEntity> roles;

    public UserDto(String userName, String pass, Collection<UserRolesEntity> roles) {
        this.userName = userName;
        this.pass = pass;
        this.roles = roles;
    }
}
