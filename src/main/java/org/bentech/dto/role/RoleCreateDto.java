package org.bentech.dto.role;

import org.bentech.entity.UserRolesEntity;

public class RoleCreateDto {
    public String roleName;

    public RoleCreateDto(String roleName) {
        this.roleName = roleName;
    }

    public RoleCreateDto() {
    }

    public UserRolesEntity to() {
        return new UserRolesEntity(
                this.roleName
        );
    }
}
