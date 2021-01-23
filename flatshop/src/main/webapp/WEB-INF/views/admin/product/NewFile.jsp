<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="test" method="post">
	Input: <input type="text" name="name">
	<button type="submit">Submit</button>
	</form>
	<p>${name}</p>
</body>
</html>