package ru.aston.nikolaev.hometask2.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

/**
 * Конфигурационный класс, который считывает информацию
 * для подключения из файла и создает подключение.
 * @author Dmitrii Nikolaev
 * @version 1.0
 */
public class Config {

    /**
     * @return возвращает Properties для чтения информации из файла.
     */
    public static Properties getProperty() {
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

    /**
     * @return возвращает подключение к БД.
     */
    public static Connection open() {
        try {
            Class.forName(getProperty().getProperty("jdbc.driver"));
            return DriverManager.getConnection(getProperty().getProperty("jdbc.url"),
                    getProperty().getProperty("jdbc.username"),
                    getProperty().getProperty("jdbc.password"));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
