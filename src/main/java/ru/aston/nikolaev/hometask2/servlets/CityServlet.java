package ru.aston.nikolaev.hometask2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.aston.nikolaev.hometask2.repository.WeatherForecastAnalysisImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * Сервлет отображает информацию по названию города,
 * получившую в парметре.
 */
@WebServlet("/city")
public class CityServlet extends HttpServlet {
    private final WeatherForecastAnalysisImpl repository = new WeatherForecastAnalysisImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cityName = req.getParameter("city");
        String rsl = repository.getAvgTempFromCity(cityName);
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.write("<h1>Информация</h1>");
            printWriter.write("<h3>");
            printWriter.write(rsl);
            printWriter.write("</h3>");
        }
    }
}
