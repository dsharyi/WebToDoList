<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Ваши задачи</title>
    <style type="text/css">
        table {
            border-collapse: collapse;
        }
        th {
            background: #aaa; /
        }
        td, th {
            border: 2px solid black; /* Параметры границы */
            padding: 5px; /* Поля в ячейках */
        }
    </style>
</head>
<body style="background-color: #c7b39b">

<jsp:useBean id="Objectives" scope="session" type="java.util.List<com.objectivelist.homework5.entity.ObjectiveEntity>"/>
<div align="center">
<div style="width: 800px" align="center">
    <c:if test="${empty Objectives}"><h2 align="center">У Вас нету задач!</h2></c:if>
    <c:if test="${!empty Objectives}">
    <h2 align="center">Ваши задачи</h2>
    <table style="border: solid" width="800">

        <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Описание</th>
            <th>Удаление</th>
            <th>Текущее состояние</th>
        </tr>

        <c:forEach items="${Objectives}" var="objectiveEntity">
            <h4>
                <tr>
                    <td>${objectiveEntity.id}</td>
                    <td>${objectiveEntity.title}</td>
                    <td>${objectiveEntity.description}</td>
                    <td><a href="${pageContext.request.contextPath}/session_remove?objectiveID=${objectiveEntity.id}">Удалить</a></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/session_edit?objectiveID=${objectiveEntity.id}">
                            <c:if test="${!objectiveEntity.done}">В процессе</c:if>
                            <c:if test="${objectiveEntity.done}">Выполнено</c:if>
                        </a>
                    </td>
            </h4>
        </c:forEach>
        </c:if>
    </table>

    <form action="/index.html">
        <button type="submit" style="margin-top: 45px">Вернуться к созданию задач</button>
    </form>
</div>
</div>

</body>
</html>