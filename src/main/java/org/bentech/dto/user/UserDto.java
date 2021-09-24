package org.bentech.dto.user;

import org.bentech.entity.UserEntity;
import org.bentech.enums.Roles;

import java.util.Collection;
import java.util.List;

public class UserDto {
    public String userName;
    public String pass;
    public Roles roles;

    public UserDto(String userName, String pass,Roles roles) {
        this.userName = userName;
        this.pass = pass;
        this.roles = roles;
    }

    public UserEntity to(UserDto current) {
        return new UserEntity(
                (this.userName == null) ? current.userName : this.userName,
                (this.pass == null) ? current.pass : this.pass,
                (this.roles == null) ? current.roles : this.roles
        );
    }
}
