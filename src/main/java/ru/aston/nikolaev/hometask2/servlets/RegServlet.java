package ru.aston.nikolaev.hometask2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.aston.nikolaev.hometask4.model.User;
import ru.aston.nikolaev.hometask4.repository.UserRepository;

import java.io.IOException;

@WebServlet("/reg")
public class RegServlet extends HttpServlet {

    private final UserRepository userRepository = new UserRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("password")
        );

        userRepository.add(user);

        HttpSession session = req.getSession();
        session.getAttribute("user");

        req.getRequestDispatcher("/user").forward(req, resp);
    }
}
