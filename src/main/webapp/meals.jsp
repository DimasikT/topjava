<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<div class="half">
    <table class="main_table">
        <tr>
            <th>Дата</th>
            <th>Описание</th>
            <th>Калории</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${meals}" var="meal">
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="half">
    <form action="meals" method="post">
        <input name="id" type="text" value="${id}" hidden>
        <label>Дата</label><br>
        <input name="date" type="datetime-local" value="${date}"><br>
        <label>Описание</label><br>
        <input name="description" type="text" value="${description}"><br>
        <label>Калории</label><br>
        <input name="calories" type="number" value="${calories}"><br>
        <input type="submit" value="${action=='update' ? 'Update' : 'Add'}">
    </form>
</div>

</body>
</html>
