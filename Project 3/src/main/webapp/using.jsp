<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prediction</title>
</head>
<body>
	<h3>
		<%
		out.println("Hello " + request.getAttribute("username"));
		%>
		<br>
		<%
		out.println("\nPlease do prediction stuff");
		%>
	</h3>
	<form action="http://localhost:8085/Project_3/DataServlet" method="get">
		<%
		String selectionText = (String) request.getAttribute("selectionList");
		%>
		Please select a car:<br><%=selectionText%><br> <input
			type="submit" value="Button Doesn't Do Anything..." name="doUsing">
	</form>
</body>
</html>