package org.bentech;

import org.bentech.dto.user.Role;
import org.bentech.entity.UserEntity;
import org.bentech.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
class Main implements CommandLineRunner {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... params) {
        UserEntity admin = new UserEntity();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRoles(new ArrayList<>(List.of(Role.ROLE_ADMIN)));

        userService.signup(admin);

        UserEntity client = new UserEntity();
        client.setUsername("client");
        client.setPassword("client");
        client.setRoles(new ArrayList<>(List.of(Role.ROLE_CLIENT)));

        userService.signup(client);
    }

}

