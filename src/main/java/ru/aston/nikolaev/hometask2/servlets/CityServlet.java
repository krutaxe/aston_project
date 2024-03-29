package ru.aston.nikolaev.hometask2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.aston.nikolaev.hometask2.repository.WeatherRepository;
import ru.aston.nikolaev.hometask2.repository.WeatherRepositoryImpl;

import java.io.IOException;

/**
 * Сервлет отображает информацию по названию города,
 * получившую в парметре.
 */
@WebServlet("/city")
public class CityServlet extends HttpServlet {
    private final WeatherRepository repository = new WeatherRepositoryImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        repository.addWeatherCurrent(req.getParameter("city"), req);
        req.getRequestDispatcher("user").forward(req, resp);
    }
}
