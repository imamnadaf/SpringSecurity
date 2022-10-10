<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
    <h2>Hello World! Jsp</h2>
    <form:form action="logout" method="POST">
        <input type="submit" value="Logout">
    </form:form>
</body>
</html>