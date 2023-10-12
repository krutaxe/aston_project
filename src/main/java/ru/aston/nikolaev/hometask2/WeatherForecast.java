package ru.aston.nikolaev.hometask2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Класс предоставляет доступ к сервису погоды.
 * Пользователь должен сам ввести название города и
 * выбрать варианты предоставления прогоза погоды.
 * Результаты запроса будут выведены на консоль и записаны в файл в папке "data"
 * @author Dmitrii Nikolaev
 * @version 1.0
 */

public class WeatherForecast {
    /**
     * Ключ для получения API сервиса
     */
    private static final String API_KEI = "7f46d59b872b85b9ef1867a31ceb1f92";

    /**
     * Метод делает GET запрос по адрессу который передается в параметры.
     * @param apiUrl адрес по которому будет выполняться GET запрос.
     * @return Метод возвращает JSON с подробной информацие о погоде.
     */
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
        System.out.println(response);
        return response.toString();
    }


    /**
     * Метод принемает от пользователя название города и делает GET запрос с учетом выбранного города.
     * Полученный результат, а это проноз погоды на трое суток каждые три часа
     * парсится в удобный формат, выводится в консоль и записывается в файл.
     * @param city Пользователь вводит название города.
     */
    public void getWeatherFor3Days(String city) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/"
                + "forecast?q=" + city + "&appid=" + API_KEI + "&units=metric";
        ObjectMapper mapper = new ObjectMapper();
        try (FileWriter writer = new FileWriter(new File("data/weatherFor3Days.txt"))) {
            JsonNode jsonNode = mapper.readTree(getResponse(apiUrl));
            StringBuilder result = new StringBuilder();
            result.append("Погода на 3 дня в городе: ").append(city).append(System.lineSeparator());
            result.append("        Дата              Температура             Осадки          Скорость ветра м/c").append(System.lineSeparator());
            for (int i = 1; i < 25; i++) {

                result.append(jsonNode.get("list")
                                .get(i)
                                .get("dt_txt"))
                        .append("           ")
                        .append(jsonNode
                                .get("list")
                                .get(i)
                                .get("main")
                                .get("temp").asInt())
                        .append("                ")
                        .append(jsonNode
                                .get("list")
                                .get(i)
                                .get("weather")
                                .get(0)
                                .get("main"))
                        .append("               ")
                        .append(jsonNode
                                .get("list")
                                .get(i)
                                .get("wind")
                                .get("speed").asInt())
                        .append(System.lineSeparator());
            }
            String weather = result.toString().replaceAll("\"", "");
            writer.write(weather);
            System.out.println(weather);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод принемает от пользователя название города и делает GET запрос с учетом выбранного города.
     * Полученный результат, а это проноз погоды на текущее время
     * парсится в удобный формат, выводится в консоль и записывается в файл.
     * @param city Пользователь вводит название города.
     */
    public void getWeatherNow(String city) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q="
                + city + "&appid=" + API_KEI + "&units=metric";
        ObjectMapper mapper = new ObjectMapper();
        try (FileWriter writer = new FileWriter(new File("data/weatherNow.txt"))) {
            JsonNode jsonNode = mapper.readTree(getResponse(apiUrl));
            StringBuilder result = new StringBuilder();
            result.append("Текущая погода в городе: ").append(city).append(System.lineSeparator());

            int temp = jsonNode.get("main").get("temp").asInt();
            String sky = jsonNode.get("weather").get(0).get("main").toString();
            int speed = jsonNode.get("wind").get("speed").asInt();

            result.append("Температура: ").append(temp).append(System.lineSeparator())
                    .append("Осадки: ").append(sky).append(System.lineSeparator())
                    .append("Скорость ветра: ").append(speed).append(" м/с");
            String weather = result.toString().replaceAll("\"", "");
            writer.write(weather);
            System.out.println(weather);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        WeatherForecast weatherForecast = new WeatherForecast();
        System.out.println("Введите название города на английском языке для получения прогноза погоды: ");
        Scanner scanner = new Scanner(System.in);
        String city = scanner.next();
        System.out.println("Если текущую погоду то введите цифру 1, если на ближайшие 3 дня то введите цифру 3");
        int flag = scanner.nextInt();
        if (flag == 1) {
            weatherForecast.getWeatherNow(city);
        } else if (flag == 3) {
            weatherForecast.getWeatherFor3Days(city);
        } else {
            System.out.println("Вы ввели не правильное значение");
        }
    }
}
