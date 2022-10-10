<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signup</title>
</head>
<body>
    <h1> Signup </h1>
    <form:form action="process-signup" method="POST" modelAttribute="signup">
        Username: <form:input path="username"/> <br/>
        Password: <form:password path="password"/> <br/>
        <input type="submit" value="Signup">
    </form:form>
</body>
</html>