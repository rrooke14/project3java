<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activity</title>
</head>
<body>
	<form action="http://localhost:8085/Project_3/DataServlet" method="get">
		You decided to
		<%
	String activity = (String) request.getAttribute("activity");
	out.println(" " + activity);
	%>
		<br> <input type="submit" value="Go to prediction page"
			name="return">
	</form>
	<%
	request.setAttribute("myact", request.getAttribute("activities"));
	%>
</body>
</html>