package org.bentech.dto.user;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class UserResponseDTO {

    @ApiModelProperty(position = 3)
    List<Role> roles;
    @ApiModelProperty(position = 0)
    private Integer id;
    @ApiModelProperty(position = 1)
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
