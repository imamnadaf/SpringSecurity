<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
</head>
<body>
    <h1> Change Password </h1>
    <c:if test="${param.invalidPassword != null}">
            <i style="color: red;"> Password not matched </i>
        </c:if>
    <form:form action="process-change-password" method="POST" modelAttribute="change-password">
         Old Password: <form:password path="oldPassword"/> <br/>
         New Password: <form:password path="newPassword"/> <br/>
         Confirm Password: <form:password path="confirmPassword"/> <br/>
         <input type="submit" value="Change">
     </form:form>
</body>
</html>