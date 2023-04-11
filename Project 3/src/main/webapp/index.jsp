<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Predictions R Us</title>
</head>
<body>
	<h1>Welcome to Predictions R Us</h1>
	<br>
	<%
	if(request.getAttribute("warning")!=null){
		String warning = (String) request.getAttribute("warning");
		out.println(warning + "\n");
	}
	%>
	<form action="/Project_3/DataServlet" method="post">
		<label>Username:</label> <input type="text" name="username"> <br>
		<label>Password:</label> <input type="password" name="password">
		<br> <input type="submit" name="login2" value="Log In">
	</form>
	<br>
</body>
</html>