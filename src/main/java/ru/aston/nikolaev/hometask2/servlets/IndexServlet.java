package ru.aston.nikolaev.hometask2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.aston.nikolaev.hometask2.repository.WeatherForecastAnalysisImpl;


import java.io.IOException;
import java.util.List;

/**
 * Сервлет отображает список городов находящтхся в БД.
 * При вводе в форму название города, можно получить по нему информацию.
 */
@WebServlet("/get")
public class IndexServlet extends HttpServlet {

    private final WeatherForecastAnalysisImpl repository = new WeatherForecastAnalysisImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        List<String> list = repository.getAllCityDB();
        getServletContext().setAttribute("ct", list);
        req.setAttribute("ct", list);
        req.getRequestDispatcher("/weather.jsp").forward(req, resp);
    }
}
