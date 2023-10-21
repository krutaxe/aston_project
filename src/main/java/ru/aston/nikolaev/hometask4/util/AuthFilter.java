package ru.aston.nikolaev.hometask4.util;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

public class AuthFilter implements Filter {

    private final Set<String> uriSet = Set.of("loginPage", "login", "addUser",
            "registration", "fail", "success");

    private boolean checkUri(String uri) {
        return uriSet.stream().anyMatch(uri::endsWith);
    }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        if (checkUri(uri)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "login.jsp");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
