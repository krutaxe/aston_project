package ru.aston.nikolaev.hometask2;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.aston.nikolaev.hometask2.service.ForecastServiceImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

/**
 * Примерно как работает Mockito вроде понял,
 * но как применить их в моём случае не разобрался.
 * Посути тут ничего не тестируется)
 */
class ForecastServiceImplTest {

    @Test
    void getResponse() {
        ForecastServiceImpl forecastServiceImpl = mock(ForecastServiceImpl.class);
        forecastServiceImpl.getResponse("https://");
        forecastServiceImpl.getResponse("https://");

        Mockito.verify(forecastServiceImpl, times(2)).getResponse("https://");

    }

    @Test
    void getWeatherFor3Days() {
        ForecastServiceImpl forecastServiceImpl = mock(ForecastServiceImpl.class);
        forecastServiceImpl.getWeatherFor3Days("city");

        Mockito.verify(forecastServiceImpl).getWeatherFor3Days("city");
    }

    @Test
    void getWeatherNow() {
        ForecastServiceImpl forecastServiceImpl = mock(ForecastServiceImpl.class);
        forecastServiceImpl.getWeatherNow("city");

        Mockito.verify(forecastServiceImpl).getWeatherNow("city");
    }
}