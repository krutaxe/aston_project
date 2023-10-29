package ru.aston.nikolaev.hometask2.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.aston.nikolaev.hometask2.util.Config;
import ru.aston.nikolaev.hometask2.util.HibernateConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeatherForecastAnalysisImpl implements WeatherForecastAnalysis {

    private final SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
    private static final String GET_CITY_MAX_TEMP = "Select city, temp FROM weather ORDER BY temp DESC LIMIT 1";
    private static final String GET_CITY_MIN_TEMP = "Select city, temp FROM weather ORDER BY temp LIMIT 1";

    private static final String GET_CITY_MAX_AVG_TEMP = "SELECT city, avg(temp) as temp FROM weather "
           + "GROUP BY city ORDER BY avg(temp) DESC LIMIT 1";

    private static final String GET_CITY_MIN_AVG_TEMP = "SELECT city, avg(temp) as temp FROM weather "
          +  "GROUP BY city ORDER BY avg(temp) LIMIT 1";

    private static final String GET_AVG_TEMP_SQL = "SELECT city, round(avg(temp), 1) as avg_temp FROM weather WHERE city = ? GROUP BY city;";
    private static final String GET_ALL_CITY = "SELECT distinct city FROM weather";

    public WeatherForecastAnalysisImpl() {

    }

    /**
     * @param sql SQL запрос.
     * @return Возвращает информацию по SQL запросу.
     */
    private String getDataForecast(String sql) {
        String city;
        String temp;
        Session session = sessionFactory.openSession();
        List<Object[]> list = session.createNativeQuery(sql).getResultList();
        city = (String) list.get(0)[0];
        temp = String.valueOf(list.get(0)[1]);
        session.close();


//        try (Connection connection = Config.open()){
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet resultSet = ps.executeQuery();
//            while (resultSet.next()) {
//                city = resultSet.getString("city");
//                temp = resultSet.getDouble("temp");
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return city + " " + temp;
    }

    /**
     * @return возвращает строку с городом и максимальной температурой.
     */
    @Override
    public String getCityWithMaxTemp() {
        return "Максимальная температура за период в городе: "
                + getDataForecast(GET_CITY_MAX_TEMP) + " градусов";
    }

    /**
     * @return возвращает строку с городом и минимальной температурой.
     */
    @Override
    public String getCityWithMinTemp() {
        return "Минимальная температура за период в городе: "
                + getDataForecast(GET_CITY_MIN_TEMP) + " градусов";
    }

    /**
     * @return возвращает строку с городом и средней максимальной температурой.
     */
    @Override
    public String getCityWithMaxAvgTemp() {
        return "Средняя максимальная температура за период в городе: "
                + getDataForecast(GET_CITY_MAX_AVG_TEMP) + " градусов";
    }

    /**
     * @return возвращает строку с городом и средней минимальной температурой.
     */
    @Override
    public String getCityWithMinAvgTemp() {
        return "Средняя минимальная температура за период в городе: "
                + getDataForecast(GET_CITY_MIN_AVG_TEMP) + " градусов";
    }

    /**
     * @return возвращает строку с городом и средней температурой.
     */
    @Override
    public String getAvgTempFromCity(String city) {
        double avgTemp = -100;
        try (Connection connection = Config.open();
             PreparedStatement ps = connection.prepareStatement(GET_AVG_TEMP_SQL)) {
            ps.setString(1, city);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                avgTemp = resultSet.getDouble("avg_temp");
                System.out.println(avgTemp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Средняя температура за период в городе: " + city + " " + avgTemp + " градусов";
    }


    /**
     * @return возвращает список городов которые есть в БД.
     */
    @Override
    public List<String> getAllCityDB() {
        List<String> cityList = new ArrayList<>();
        try (Connection connection = Config.open();
             PreparedStatement ps = connection.prepareStatement(GET_ALL_CITY)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                cityList.add(resultSet.getString("city"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cityList;
    }
}
