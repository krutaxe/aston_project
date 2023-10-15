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
<h2>Список городов:</h2>
<%
    List<String> list = (List) request.getAttribute("ct");
%>

<% for (String s : list) { %>
    <tr>
        <td>
            <h3>
                <ul>
                    <%=s%>
                </ul>
            </h3>
        </td>
    </tr>
<%} %>
<br>
<br>

<form action="city" method="get">
    <input type="text" name="city"
           placeholder="Введите город"/>

    <input type="submit" value="ЖМИ!">
</form>
<br/>
<br/>

<form action="stat" method="get">
    <input type="submit" value="Статистика">
</form>




</body>
</html>
