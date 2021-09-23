package org.bentech.dto.user;

import org.bentech.entity.UserEntity;
import org.bentech.entity.UserRolesEntity;

import java.util.List;

public class UserCreateDto {
    public String userName;
    public String pass;
    public List<UserRolesEntity> roles;

    public UserCreateDto(String userName, String pass, List<UserRolesEntity> roles) {
        this.userName = userName;
        this.pass = pass;
        this.roles = roles;
    }

    public UserCreateDto() {
    }

    public UserEntity to() {
        return new UserEntity(
                this.userName,
                this.pass,
                this.roles
        );
    }
}
