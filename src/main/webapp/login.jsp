<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Weather</title>
</head>
<body>
<h1>Добро пожаловать!</h1>
</br>

<form action="user" method="get">
    <input type="text" name="login" placeholder="Введите логин"/>
    </br>
    </br>
    <input type="text" name="password" placeholder="Введите пароль"/>
    </br>
    </br>
    <input type="submit" value="Войти">
</form>

</br>

<form action="reg.jsp" method="get">
    <input type="submit" value="Регистрация">
</form>

</br>

<br/>
<br/>
</body>
</html>
