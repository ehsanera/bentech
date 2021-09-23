package org.bentech.dto.user;

import org.bentech.entity.UserEntity;
import org.bentech.entity.UserRolesEntity;

import java.util.Collection;
import java.util.List;

public class UserCreateDto {
    public String userName;
    public String pass;
    public List<Long> roles;

    public UserCreateDto(String userName, String pass, List<Long> roles) {
        this.userName = userName;
        this.pass = pass;
        this.roles = roles;
    }

    public UserCreateDto() {
    }

    public UserEntity to(Collection<UserRolesEntity> roles) {
        return new UserEntity(
                this.userName,
                this.pass,
                roles
        );
    }
}
