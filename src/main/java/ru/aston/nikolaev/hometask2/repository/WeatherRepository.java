package ru.aston.nikolaev.hometask2.repository;

import jakarta.servlet.http.HttpServletRequest;
import ru.aston.nikolaev.hometask2.model.Weather;

import java.util.List;

/**
 * Данный интерфейс добавляет прогноз погоды в БД
 * @author Dmitrii Nikolaev
 * @version 1.0
 */
public interface WeatherRepository {

    void addWeatherFor3Day(String city);

    void addWeatherCurrent(String city, HttpServletRequest request);

    List<Weather> getWeatherListFromUserId(int id, HttpServletRequest request);

    List<Weather> getAllWeather();

}
