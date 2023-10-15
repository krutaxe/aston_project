package ru.aston.nikolaev.hometask2.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.aston.nikolaev.hometask2.model.Weather;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс предоставляет доступ к сервису погоды.
 * Пользователь должен сам ввести название города и
 * выбрать варианты предоставления прогоза погоды.
 * Результаты запроса будут выведены на консоль и записаны в файл в папке "data"
 *
 * @author Dmitrii Nikolaev
 * @version 1.0
 */

public class ForecastServiceImpl implements ForecastService {
    /**
     * Ключ для получения API сервиса
     */
    private static final String API_KEI = "7f46d59b872b85b9ef1867a31ceb1f92";

    /**
     * Метод делает GET запрос по адрессу который передается в параметры.
     *
     * @param apiUrl адрес по которому будет выполняться GET запрос.
     * @return Метод возвращает JSON с подробной информацие о погоде.
     */
    @Override
    public String getResponse(String apiUrl) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String readLine;
                while ((readLine = reader.readLine()) != null) {
                    response.append(readLine);
                }
                reader.close();
            } else {
                System.out.println("Ошибка запроса: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }


    /**
     * Метод принемает от пользователя название города и делает GET запрос с учетом выбранного города.
     * Полученный результат, а это проноз погоды на пять суток каждые 12 часов
     * парсится в удобный формат и записывается в файл.
     *
     * @param city Пользователь вводит название города.
     * @return возвращает список объетов Weather с данными о прогнозе погоды с интервалом 12 часов.
     */
    @Override
    public List<Weather> getWeatherFor3Days(String city) {
        SimpleDateFormat format = new SimpleDateFormat("\"yyyy-MM-d H:m:ss\"");
        List<Weather> weatherList = new ArrayList<>();
        String apiUrl = "https://api.openweathermap.org/data/2.5/"
                + "forecast?q=" + city + "&appid=" + API_KEI + "&units=metric";
        ObjectMapper mapper = new ObjectMapper();
        try (FileWriter writer = new FileWriter("data/weatherFor3Days.txt")) {
            JsonNode jsonNode = mapper.readTree(getResponse(apiUrl));
            StringBuilder result = new StringBuilder();
            result.append("Погода на 3 дня в городе: ").append(city).append(System.lineSeparator());
            result.append("        Дата              Температура             Осадки          Скорость ветра м/c")
                    .append(System.lineSeparator());

            for (int i = 1; i < 40; i += 4) {
                Weather weather = new Weather();
                weather.setCity(city);
                weather.setTemp(jsonNode.get("list").get(i).get("main").get("temp").asInt());
                weather.setSky(jsonNode.get("list").get(i).get("weather").get(0).get("main").toString());
                weather.setWindSpeed(jsonNode.get("list").get(i).get("wind").get("speed").asInt());
                Date date = format.parse(jsonNode.get("list").get(i).get("dt_txt").toString());

                weather.setDate(new Timestamp(date.getTime()));
                weatherList.add(weather);
                result.append(jsonNode.get("list").get(i).get("dt_txt")).append("           ")
                        .append(weather.getTemp()).append("                ")
                        .append(weather.getSky()).append("               ")
                        .append(weather.getWindSpeed())
                        .append(System.lineSeparator());
            }
            String rsl = result.toString().replaceAll("\"", "");
            writer.write(rsl);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return weatherList;
    }

    /**
     * Метод принемает от пользователя название города и делает GET запрос с учетом выбранного города.
     * Полученный результат, а это проноз погоды на текущее время
     * парсится в удобный формат и записывается в файл.
     * @param city Пользователь вводит название города.
     * @return возвращает объект Weather с данными о прогнозе погоды на текущий момент.
     */
    @Override
    public Weather getWeatherNow(String city) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q="
                + city + "&appid=" + API_KEI + "&units=metric";
        ObjectMapper mapper = new ObjectMapper();
        Weather weatherObj = new Weather();

        try (FileWriter writer = new FileWriter("data/weatherNow.txt")) {
            JsonNode jsonNode = mapper.readTree(getResponse(apiUrl));
            StringBuilder result = new StringBuilder();
            result.append("Текущая погода в городе: ").append(city).append(System.lineSeparator());

            weatherObj.setTemp(jsonNode.get("main").get("temp").asInt());
            weatherObj.setSky(jsonNode.get("weather").get(0).get("main").toString().replaceAll("\"", ""));
            weatherObj.setWindSpeed(jsonNode.get("wind").get("speed").asInt());
            weatherObj.setCity(city);


            result.append("Температура: ").append(weatherObj.getTemp()).append(System.lineSeparator())
                    .append("Осадки: ").append(weatherObj.getSky()).append(System.lineSeparator())
                    .append("Скорость ветра: ").append(weatherObj.getWindSpeed()).append(" м/с").append(System.lineSeparator())
                    .append("Дата: ").append(LocalDateTime.now());
            String weather = result.toString();
            writer.write(weather);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return weatherObj;
    }
}
