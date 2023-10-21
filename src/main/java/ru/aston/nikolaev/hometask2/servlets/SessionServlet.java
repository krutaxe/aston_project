package ru.aston.nikolaev.hometask2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.aston.nikolaev.hometask2.model.Weather;
import ru.aston.nikolaev.hometask2.repository.WeatherRepository;
import ru.aston.nikolaev.hometask2.repository.WeatherRepositoryImpl;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toMap;

@WebServlet("/content")
public class SessionServlet extends HttpServlet {

    private final WeatherRepository repository = new WeatherRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        req.setAttribute("list", repository.getAllWeather());


        req.getRequestDispatcher("test.jsp").forward(req, resp);
    }
}
