CREATE TABLE users (
    id serial PRIMARY KEY,
    name varchar(20) not null,
    login varchar(20) not null unique,
    password varchar(20) not null
);