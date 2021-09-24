package org.bentech.entity;

import org.bentech.dto.user.UserDto;
import org.bentech.enums.Roles;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "user_name", length = 50, nullable = false, unique = true)
    String userName;

    @Column(name = "pass", length = 200, nullable = false)
    String pass;

    @Column(name = "roles", nullable = false)
    @Enumerated(EnumType.STRING)
    Roles roles;


    public UserEntity(String userName, String pass, Roles roles) {
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