package org.bentech.entity;

import org.bentech.dto.UserDto;
import org.bentech.enums.Roles;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "pass", length = 200, nullable = false)
    private String pass;

    @Column(name = "roles", length = 200, nullable = false)
    private Roles roles;

    public UserDto toDto() {
        return new UserDto(
                this.id,
                this.name,
                this.roles
        );
    }
}