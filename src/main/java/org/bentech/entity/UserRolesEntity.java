package org.bentech.entity;

import org.bentech.dto.role.RoleDto;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class UserRolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name", length = 200, nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Collection<UserEntity> users;

    public UserRolesEntity( String roleName) {
        this.roleName = roleName;
    }

    public UserRolesEntity() {

    }

    public RoleDto toDto() {
        return new RoleDto(
                this.id,
                this.roleName
        );
    }
}