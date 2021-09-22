CREATE TABLE users(
    id serial,
    name varchar(50) NOT NULL unique,
    pass varchar(200) NOT NULL,
    roles varchar(200) NOT NULL
);