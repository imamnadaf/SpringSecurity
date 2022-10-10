<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>

    <c:if test="${param.error != null}">
        <i style="color: red;"> Invalid login or password </i>
    </c:if>

    <c:if test="${param.logout != null}">
        <i style="color: green;"> You are successfully logout! Login again</i>
    </c:if>

    <h1> My Custom login page </h1>
    <form:form>
        Username: <input type="text" name="username"> <br/>
        Password: <input type="password" name="password"> <br/>
        <input type="submit" value="Login">
    </form:form>
</body>
</html>