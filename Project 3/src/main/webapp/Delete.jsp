<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	String instance = (String) request.getAttribute("deleted");
	%>
	<%
	out.println("You deleted " + instance);
	%><br>
	<form action="http://localhost:8085/Project_3/DataServlet"
		method="post" name="selection">
		<input type="submit" value="Return to Prediction Screen" name="delete">
	</form>
</body>
</html>