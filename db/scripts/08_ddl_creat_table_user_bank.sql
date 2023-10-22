CREATE TABLE user_bank (
    id serial PRIMARY KEY,
    name varchar(20) not null,
    id_account int references bank_account(id)
);