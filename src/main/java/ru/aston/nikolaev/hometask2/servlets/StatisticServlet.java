package ru.aston.nikolaev.hometask2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.aston.nikolaev.hometask2.repository.WeatherForecastAnalysis;
import ru.aston.nikolaev.hometask2.repository.WeatherForecastAnalysisImpl;
import ru.aston.nikolaev.hometask4.model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


/**
 * Сервлет отображает статистику погоды.
 */
@WebServlet("/stat")
public class StatisticServlet extends HttpServlet {
    private final WeatherForecastAnalysis analysis = new WeatherForecastAnalysisImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String maxTemp = analysis.getCityWithMaxTemp();
        String minTemp = analysis.getCityWithMinTemp();
        String maxAvgTemp = analysis.getCityWithMaxAvgTemp();
        String minAvgTemp = analysis.getCityWithMinAvgTemp();



        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");

        PrintWriter printWriter = resp.getWriter();
        printWriter.write("<h1>Статистика погоды</h1>");
        printWriter.write("<h3>");
        printWriter.write(maxTemp + "<br/>" + "<br/>");
        printWriter.write(minTemp + "<br/>" + "<br/>");
        printWriter.write(maxAvgTemp + "<br/>" + "<br/>");
        printWriter.write(minAvgTemp + "<br/>" + "<br/>");
        printWriter.write(user.getName() + "<br/>" + "<br/>");
        printWriter.write("</h3>" + "<br/>" + "<br/>");

    }
}
