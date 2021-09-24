package org.bentech.dto.user;

import org.bentech.entity.UserEntity;
import org.bentech.enums.Roles;

public class UserUpdateDto {
    public String userName;
    public String pass;
    public Roles roles;

    public UserUpdateDto(String userName, String pass, Roles roles) {
        this.userName = userName;
        this.pass = pass;
        this.roles = roles;
    }

    public UserUpdateDto() {
    }
}
