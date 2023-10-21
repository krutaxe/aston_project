<%--@elvariable id="list" type="java.util.List"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<H1>Weather</H1>
<ul>
    <c:forEach var="weather" items="${list}">
        <li>${weather.city}</li>
    </c:forEach>
</ul>

</body>
</html>
