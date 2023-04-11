<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activity</title>
</head>
<body>
	<form action="http://localhost:8085/Project_3/DataServlet"
		method="post">

		<%
		String activity = (String) request.getAttribute("activity");
		out.println("You should " + (String) activity);
		%>
		<br> <input type="submit" value="Good Idea" name="return1">
		<input type="submit" value="Bad Idea" name="return2">
	</form>
	<%
	request.setAttribute("myact", activity);
	String user = (String) request.getAttribute("username");
	String pw = (String) request.getAttribute("password");
	request.setAttribute("password", pw);
	request.setAttribute("username", user);
	%>
</body>
</html>