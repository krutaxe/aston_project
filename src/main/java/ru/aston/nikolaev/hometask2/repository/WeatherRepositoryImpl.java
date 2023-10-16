package ru.aston.nikolaev.hometask2.repository;

import ru.aston.nikolaev.hometask2.service.ForecastService;
import ru.aston.nikolaev.hometask2.service.ForecastServiceImpl;
import ru.aston.nikolaev.hometask2.model.Weather;
import ru.aston.nikolaev.hometask2.util.Config;

import java.sql.*;
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

    private static final String ADD_WEATHER_CURRENT_SQL = "INSERT INTO Weather(city, temp, sky, speed)" +
            "values (?, ?, ?, ?)";

    /**
     * Добавляет текущую погоду в БД по названию города.
     * @param city Название города
     */
    @Override
    public void addWeatherCurrent(String city) {
        Weather weather = forecastService.getWeatherNow(city);

        try (Connection connection = Config.open();
             PreparedStatement ps = connection.prepareStatement(ADD_WEATHER_CURRENT_SQL,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, weather.getCity());
            ps.setInt(2, weather.getTemp());
            ps.setString(3, weather.getSky().replaceAll("\"", ""));
            ps.setInt(4, weather.getWindSpeed());
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
