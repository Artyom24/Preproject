<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    <%@include file="styles/w3.css"%>
</style>
<%--
  Created by IntelliJ IDEA.
  User: Artyom
  Date: 05.09.2019
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User</title>
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Java Mentor</h1>
</div>

<div class="w3-container w3-padding">

    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Hello, user!</h2>
        </div>
     </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/user?sign=out'">Sign out</button>
</div>
</body>
</html>