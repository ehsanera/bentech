package org.bentech.dto.user;

import org.bentech.enums.Roles;

import javax.validation.constraints.Min;

public class UserCreateDto {
    @Min(3)
    public String userName;
    @Min(5)
    public String pass;
    public Roles roles;

    public UserCreateDto(String userName, String pass, Roles roles) {
        this.userName = userName;
        this.pass = pass;
        this.roles = roles;
    }

    public UserCreateDto() {
    }
}
