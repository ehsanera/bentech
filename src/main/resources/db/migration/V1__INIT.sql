CREATE SEQUENCE hibernate_sequence START 1;

CREATE TABLE users(
    user_name varchar(50) NOT NULL unique primary key,
    pass varchar(200) NOT NULL,
    roles varchar(200) NOT NULL
);

CREATE TABLE roles(
    id BIGSERIAL NOT NULL primary key,
    role_name varchar(200) NOT NULL
);

