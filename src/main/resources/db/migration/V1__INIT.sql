CREATE SEQUENCE hibernate_sequence START 1;

CREATE TABLE users(
    id bigserial primary key,
    username varchar(50) NOT NULL unique,
    password varchar(200) NOT NULL,
    roles integer NOT NULL
);