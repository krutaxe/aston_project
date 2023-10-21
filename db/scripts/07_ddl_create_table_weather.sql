CREATE TABLE weather (
                       id serial PRIMARY KEY,
                       city varchar(20) not null,
                       temp int,
                       sky varchar(20),
                       speed int,
                       date timestamp,
                       user_id int references users(id)
);