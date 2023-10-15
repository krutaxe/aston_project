package ru.aston.nikolaev.hometask2.service;

import ru.aston.nikolaev.hometask2.model.Weather;

import java.util.List;

public interface ForecastService {

    String getResponse(String apiUrl);

    List<Weather> getWeatherFor3Days(String city);

    Weather getWeatherNow(String city);
}
