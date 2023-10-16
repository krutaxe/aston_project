CREATE TABLE driver (
    id serial PRIMARY KEY,
    name varchar(20) not null unique,
    age int
);