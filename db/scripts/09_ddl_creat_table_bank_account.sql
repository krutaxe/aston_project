CREATE TABLE bank_account (
    id serial PRIMARY KEY,
    amount int check ( amount >= 0 )
);