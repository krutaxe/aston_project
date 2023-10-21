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
import ru.aston.nikolaev.hometask4.model.User;
import ru.aston.nikolaev.hometask4.repository.UserRepository;
import ru.aston.nikolaev.hometask4.util.SessionHttp;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final UserRepository userRepository = new UserRepository();
    WeatherRepository repository = new WeatherRepositoryImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login != null && password != null) {
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("password", password);
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        User user = userRepository.checkUser((String) req.getSession().getAttribute("login"),
                (String) req.getSession().getAttribute("password"));

        if (user.getName() == null) {
            req.getRequestDispatcher("reLogin.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user", user);
            List<Weather> weatherList = repository.getWeatherListFromUserId(user.getId(), req);
            req.setAttribute("weatherList", weatherList);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
