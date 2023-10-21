<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="user" type=""--%>
<%@ page import="ru.aston.nikolaev.hometask4.model.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Weather</title>
</head>
<body>
<h1>Главное меню</h1>
<h2>История запросов пользователя: ${user.name}</h2>

<ul>
    <c:forEach var="weather" items="${weatherList}">
        <li>
           Город: ${weather.city}, Температура: ${weather.temp}, Осадки: ${weather.sky}, Ветер: ${weather.windSpeed}, Дата: ${weather.date}
        </li>
    </c:forEach>
</ul>


<form action="city" method="get">
    <input type="text" name="city"
           placeholder="Введите город"/>

    <input type="submit" value="Новый запрос">
</form>
<br/>
<br/>

<form action="stat" method="get">
    <input type="submit" value="Статистика">
</form>

<form action="exit" method="get">
    <input type="submit" value="Выйти">
</form>

<br/>
<br/>


</body>
</html>
