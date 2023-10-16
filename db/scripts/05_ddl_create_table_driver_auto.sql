CREATE TABLE driver_auto (
    driver_id int PRIMARY KEY references driver(id),
    auto_id int PRIMARY KEY references auto(id)
)