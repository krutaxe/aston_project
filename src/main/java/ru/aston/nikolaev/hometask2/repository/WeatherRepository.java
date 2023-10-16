package ru.aston.nikolaev.hometask2.repository;
/**
 * Данный интерфейс добавляет прогноз погоды в БД
 * @author Dmitrii Nikolaev
 * @version 1.0
 */
public interface WeatherRepository {

    void addWeatherFor3Day(String city);

    void addWeatherCurrent(String city);

}
