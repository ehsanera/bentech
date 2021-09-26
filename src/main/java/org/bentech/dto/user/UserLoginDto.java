package org.bentech.dto.user;

import org.bentech.enums.Roles;

import javax.validation.constraints.Min;

public class UserLoginDto {
    @Min(3)
    public String userName;
    @Min(3)
    public String pass;

    public UserLoginDto(String userName, String pass, Roles roles) {
        this.userName = userName;
        this.pass = pass;
    }

    public UserLoginDto() {
    }
}
