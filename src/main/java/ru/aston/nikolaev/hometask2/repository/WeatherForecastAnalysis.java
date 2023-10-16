package ru.aston.nikolaev.hometask2.repository;

import java.util.List;

/**
 * Данный интерфейс содержит методы для
 * получения статистики погоды из базы данных.
 *
 * @author Dmitrii Nikolaev
 * @version 1.0
 */
public interface WeatherForecastAnalysis {
    /**
     * @return возвращает строку с городом и максимальной температурой.
     */
    String getCityWithMaxTemp();
    /**
     * @return возвращает строку с городом и минимальной температурой.
     */
    String getCityWithMinTemp();
    /**
     * @return возвращает строку с городом и средней максимальной температурой.
     */
    String getCityWithMaxAvgTemp();
    /**
     * @return возвращает строку с городом и средней минимальной температурой.
     */
    String getCityWithMinAvgTemp();
    /**
     * @return возвращает строку с городом и средней температурой.
     */
    String getAvgTempFromCity(String city);
    /**
     * @return возвращает список городов которые есть в БД.
     */
    List<String> getAllCityDB();
}
