<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	We have sent a code to your email. <br/>
	Please let us know you received the code. <br/> 
	Please enter the code: <br/>
	<form action="verify" method="post">
		<input type="text" name="code" required="required"/> <input type="submit" value="Submit"/>
	</form>
	
	<a href="resend-code">Resend the code to me</a><br/>
	<p>${message}</p>
	<c:if test="${active != null}">
		<a href="login">Login</a>
	</c:if>
</body>
</html>