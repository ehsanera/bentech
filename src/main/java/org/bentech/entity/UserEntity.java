package org.bentech.entity;

import org.bentech.dto.UserDto;
import org.bentech.enums.Roles;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "userName", length = 50, nullable = false, unique = true)
    private String userName;

    @Column(name = "pass", length = 200, nullable = false)
    private String pass;

    @Column(name = "roles", length = 200, nullable = false)
    @ElementCollection
    private List<Roles> roles;

    public UserDto toDto() {
        return new UserDto(
                this.userName,
                this.pass,
                this.roles
        );
    }
}