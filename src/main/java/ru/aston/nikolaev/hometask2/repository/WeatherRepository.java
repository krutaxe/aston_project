package ru.aston.nikolaev.hometask2.repository;

import java.sql.Connection;
import java.util.List;

public interface WeatherRepository {
    void addWeatherFor3Day(String city);

    void addWeatherCurrent(String city);

}
