package ru.aston.nikolaev.hometask2;

import ru.aston.nikolaev.hometask2.repository.WeatherForecastAnalysis;
import ru.aston.nikolaev.hometask2.repository.WeatherForecastAnalysisImpl;

public class Main {
    public static void main(String[] args){
        WeatherForecastAnalysis weatherForecastAnalysis = new WeatherForecastAnalysisImpl();
        System.out.println(weatherForecastAnalysis.getAllCityDB());
    }
}
