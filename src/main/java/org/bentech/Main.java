package org.bentech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.bentech.repository")
class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}


