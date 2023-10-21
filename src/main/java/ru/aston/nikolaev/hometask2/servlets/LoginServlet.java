package ru.aston.nikolaev.hometask2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.aston.nikolaev.hometask2.repository.WeatherForecastAnalysisImpl;
import ru.aston.nikolaev.hometask4.model.User;
import ru.aston.nikolaev.hometask4.repository.UserRepository;
import ru.aston.nikolaev.hometask4.util.AuthFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Сервлет отображает список городов находящтхся в БД.
 * При вводе в форму название города, можно получить по нему информацию.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final WeatherForecastAnalysisImpl repository = new WeatherForecastAnalysisImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
