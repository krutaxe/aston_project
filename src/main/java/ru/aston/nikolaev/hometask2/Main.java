package ru.aston.nikolaev.hometask2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.aston.nikolaev.hometask2.model.Weather;
import ru.aston.nikolaev.hometask2.repository.WeatherForecastAnalysis;
import ru.aston.nikolaev.hometask2.repository.WeatherForecastAnalysisImpl;
import ru.aston.nikolaev.hometask2.util.HibernateConfig;

import java.util.List;

public class Main {

        public static SessionFactory sf = HibernateConfig.getSessionFactory();
    public static void main(String[] args) {
    }
}
