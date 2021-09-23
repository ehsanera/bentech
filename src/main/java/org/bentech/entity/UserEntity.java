package org.bentech.entity;

import org.bentech.dto.user.UserDto;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "user_name", length = 50, nullable = false, unique = true)
    private String userName;

    @Column(name = "pass", length = 200, nullable = false)
    private String pass;

    @ManyToMany
    @JoinTable(
            name = "roles",
            joinColumns = @JoinColumn(
                    name = "user_name", referencedColumnName = "user_name"),
            inverseJoinColumns = @JoinColumn(
                    name = "roles_id", referencedColumnName = "id"))
    private Collection<UserRolesEntity> roles;

    public UserEntity(String userName, String pass, Collection<UserRolesEntity> roles) {
        this.userName = userName;
        this.pass = pass;
        this.roles = roles;
    }

    public UserEntity() {

    }

    public UserDto toDto() {
        return new UserDto(
                this.userName,
                this.pass,
                this.roles
        );
    }
}