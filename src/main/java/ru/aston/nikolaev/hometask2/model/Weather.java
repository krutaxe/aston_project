package ru.aston.nikolaev.hometask2.model;

import java.sql.Timestamp;
import java.util.StringJoiner;

/**
 * Класс описывает модель "погода",
 * со своими свойсвами для загрузки и получения
 * из базы данных.
 *
 * @author Dmitrii Nikolaev
 * @version 1.0
 */
public class Weather {
    /**
     * Primary key
     */
    private int id;
    /**
     * Название города
     */
    private String city;
    /**
     * Температура
     */
    private int temp;
    /**
     * Облачность
     */
    private String sky;
    /**
     * Скорость ветра
     */
    private int windSpeed;
    /**
     * Дата прогноза.
     */
    private Timestamp date;

    public Weather(int id, String city, int temp, String sky, int windSpeed, Timestamp date) {
        this.id = id;
        this.city = city;
        this.temp = temp;
        this.sky = sky;
        this.windSpeed = windSpeed;
        this.date = date;
    }

    public Weather() {
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Weather.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("city='" + city + "'")
                .add("temp=" + temp)
                .add("sky='" + sky + "'")
                .add("windSpeed=" + windSpeed)
                .add("date=" + date)
                .toString();
    }
}
