package ru.aston.nikolaev.hometask4.repository;

import ru.aston.nikolaev.hometask2.util.Config;
import ru.aston.nikolaev.hometask4.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {

    public Optional<User> add(User user) {
        Optional<User> rsl;
        try (Connection connection = Config.open()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users (name, login, password) " +
                    "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
            rsl = Optional.of(user);
        } catch (SQLException e) {
            rsl = Optional.empty();
        }
        return rsl;
    }

    public User checkUser(String login, String pwd) {
        User user = new User();
        try (Connection connection = Config.open()) {
            PreparedStatement ps = connection.prepareStatement("select * from users where login = ? and password = ?");
            ps.setString(1, login);
            ps.setString(2, pwd);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
