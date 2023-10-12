package ru.aston.nikolaev.hometask2;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

/**
 * Примерно как работает Mockito вроде понял,
 * но как применить их в моём случае не разобрался.
 * Посути тут ничего не тестируется)
 */
class WeatherForecastTest {

    @Test
    void getResponse() {
        WeatherForecast weatherForecast = mock(WeatherForecast.class);
        weatherForecast.getResponse("https://");
        weatherForecast.getResponse("https://");

        Mockito.verify(weatherForecast, times(2)).getResponse("https://");

    }

    @Test
    void getWeatherFor3Days() {
        WeatherForecast weatherForecast = mock(WeatherForecast.class);
        weatherForecast.getWeatherFor3Days("city");

        Mockito.verify(weatherForecast).getWeatherFor3Days("city");
    }

    @Test
    void getWeatherNow() {
        WeatherForecast weatherForecast = mock(WeatherForecast.class);
        weatherForecast.getWeatherNow("city");

        Mockito.verify(weatherForecast).getWeatherNow("city");
    }
}