package ru.aston.nikolaev.hometask2.repository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.aston.nikolaev.hometask2.service.ForecastService;
import ru.aston.nikolaev.hometask2.service.ForecastServiceImpl;
import ru.aston.nikolaev.hometask2.model.Weather;
import ru.aston.nikolaev.hometask2.util.Config;
import ru.aston.nikolaev.hometask4.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Данный класс длбавляет прогноз погоды в БД.
 * @author Dmitrii Nikolaev
 * @version 1.0
 */
public class WeatherRepositoryImpl implements WeatherRepository {
    private final ForecastService forecastService = new ForecastServiceImpl();

    private static final String ADD_WEATHER_FOR_3_DAY_SQL = "INSERT INTO Weather(city, temp, sky, speed, date)" +
            "values (?, ?, ?, ?, ?)";

    private static final String ADD_WEATHER_CURRENT_SQL = "INSERT INTO Weather(city, temp, sky, speed, date, user_id)" +
            "values (?, ?, ?, ?, ?, ?)";
    private static final String GET_WEATHER_FROM_USER_ID_SQL = "SELECT * FROM weather WHERE user_id = ?";
    private static final String GET_ALL_WEATHER = "SELECT * FROM weather";


    @Override
    public List<Weather> getAllWeather() {
        List<Weather> weatherList = new ArrayList<>();
        try (Connection connection = Config.open()){
            PreparedStatement ps = connection.prepareStatement(GET_ALL_WEATHER);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                weatherList.add(new Weather(
                        resultSet.getInt("id"),
                        resultSet.getString("city"),
                        resultSet.getInt("temp"),
                        resultSet.getString("sky"),
                        resultSet.getInt("speed"),
                        resultSet.getTimestamp("date")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return weatherList;
    }

    @Override
    public List<Weather> getWeatherListFromUserId(int id, HttpServletRequest request) {
        List<Weather> weatherList = new ArrayList<>();
        User user = (User) request.getSession().getAttribute("user");
        try (Connection connection = Config.open()){
            PreparedStatement ps = connection.prepareStatement(GET_WEATHER_FROM_USER_ID_SQL);
            ps.setInt(1, user.getId());
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                weatherList.add(new Weather(
                        resultSet.getInt("id"),
                        resultSet.getString("city"),
                        resultSet.getInt("temp"),
                        resultSet.getString("sky"),
                        resultSet.getInt("speed"),
                        resultSet.getTimestamp("date")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return weatherList;
    }

    /**
     * Добавляет текущую погоду в БД по названию города.
     * @param city Название города
     */


    @Override
    public void addWeatherCurrent(String city, HttpServletRequest req) {
        Weather weather = forecastService.getWeatherNow(city);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        try (Connection connection = Config.open();
             PreparedStatement ps = connection.prepareStatement(ADD_WEATHER_CURRENT_SQL,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, weather.getCity());
            ps.setInt(2, weather.getTemp());
            ps.setString(3, weather.getSky().replaceAll("\"", ""));
            ps.setInt(4, weather.getWindSpeed());
            ps.setTimestamp(5, weather.getDate());
            ps.setInt(6, user.getId());
            ps.execute();

            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    weather.setId(id.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Добавляет прогноз погоды на 5 дней в БД по названию города.
     * @param city Название города
     */
    @Override
    public void addWeatherFor3Day(String city) {
        List<Weather> weatherList = forecastService.getWeatherFor3Days(city);
        for(Weather weather : weatherList) {
            try (Connection connection = Config.open();
                 PreparedStatement ps = connection.prepareStatement(ADD_WEATHER_FOR_3_DAY_SQL,
                         PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, weather.getCity());
                ps.setInt(2, weather.getTemp());
                ps.setString(3, weather.getSky().replaceAll("\"", ""));
                ps.setInt(4, weather.getWindSpeed());
                ps.setTimestamp(5, weather.getDate());
                ps.execute();

                try (ResultSet id = ps.getGeneratedKeys()) {
                    if (id.next()) {
                        weather.setId(id.getInt(1));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
