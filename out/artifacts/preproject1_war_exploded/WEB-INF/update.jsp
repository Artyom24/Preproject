<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entities.User" %>
<style>
    <%@include file="styles/w3.css"%>
</style>
<%--
  Created by IntelliJ IDEA.
  User: Artyom
  Date: 25.08.2019
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Java Mentor</title>
    </head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Java Mentor</h1>
</div>

<div class="w3-container w3-padding">

    <div class="w3-card-4">
        <div class="w3-container w3-center w3-blue-gray">
            <h2>Update user</h2>
        </div>

        <c:set var="user" value="${requestScope.user}" />
        <form method="post" class="w3-selection w3-light-grey w3-padding">
            <label>Name: <input type="text" name="name" value="${user.name}" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%" required><br /> </label>
            <label>Login: <input type="text" name="login" value="${user.login}" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%" required><br /> </label>
            <label>Password: <input type="text" name="pass" value="${user.password}" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%" required><br /> </label>
            <p>Current Role: ${user.role}<Br>
                <input type="radio" name="role" value="user" checked> user<Br>
                <input type="radio" name="role" value="admin"> admin
            </p>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Submit</button>
        </form>

        <c:if test="${pageContext.response.status eq 406}">
            <div class="w3-panel w3-red w3-display-container w3-card-4 w3-round">
                <span onclick="this.parentElement.style.display='none'"class="w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey">×</span>
                <h5>Login is already taken. Please choose a different login!</h5>
            </div>
        </c:if>

    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">List of users</button>
</div>
</body>
</html>
