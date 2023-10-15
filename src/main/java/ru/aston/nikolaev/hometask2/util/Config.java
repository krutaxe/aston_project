package ru.aston.nikolaev.hometask2.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;


public class Config {
    public static Properties loadConfig() {
        Properties cfg = new Properties();
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     Objects.requireNonNull(Config.class.getClassLoader()
                                             .getResourceAsStream("db.properties"))
                             ))) {
            cfg.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cfg;
    }

    public static Connection open() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(loadConfig().getProperty("jdbc.url"),
                    loadConfig().getProperty("jdbc.username"),
                    loadConfig().getProperty("jdbc.password"));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
