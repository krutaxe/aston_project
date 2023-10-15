package ru.aston.nikolaev.hometask2.repository;

import java.util.List;
import java.util.Optional;

public interface WeatherForecastAnalysis {
    String getCityWithMaxTemp();
    String getCityWithMinTemp();
    String getCityWithMaxAvgTemp();
    String getCityWithMinAvgTemp();
    String getAvgTempFromCity(String city);
    List<String> getAllCityDB();
}
