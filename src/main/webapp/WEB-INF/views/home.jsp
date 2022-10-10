<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
    <h1> Hi ${username} </h1>
    <h4> Roles assigned: ${authorities}</h4>

    <sec:authorize access='hasAuthority("Trainer")'>
        <a href="/SpringSecurity/trainer"> Show Trainers Dashboard</a> <br/>
    </sec:authorize>

    <sec:authorize access='hasAuthority("Coder")'>
        <a href="/SpringSecurity/coder"> Show Coders Dashboard</a> <br/>
    </sec:authorize>


    <form:form action="logout" method="POST">
        <input type="submit" value="logout">
    </form:form>
</body>
</html>