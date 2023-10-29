package ru.aston.nikolaev.hometask2.model;

import jakarta.persistence.*;

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

@Entity
@Table(name = "weather")
public class Weather {
    /**
     * Primary key
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Название города
     */
    @Column(name = "city")
    private String city;
    /**
     * Температура
     */
    @Column(name = "temp")
    private int temp;
    /**
     * Облачность
     */
    @Column(name = "sky")
    private String sky;
    /**
     * Скорость ветра
     */
    @Column(name = "speed")
    private int windSpeed;
    /**
     * Дата прогноза.
     */
    @Column(name = "date")
    private Timestamp date;

    @Column(name = "user_id")
    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
