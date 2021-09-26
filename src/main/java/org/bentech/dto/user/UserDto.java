package org.bentech.dto.user;

public class UserDto {
    public String userName;
    public String pass;
    public Role roles;

    public UserDto(String userName, String pass, Role roles) {
        this.userName = userName;
        this.pass = pass;
        this.roles = roles;
    }
}
