CREATE TABLE driver_auto (
    driver_id int references driver(id),
    auto_id int references auto(id),
    PRIMARY KEY (driver_id, auto_id)
)