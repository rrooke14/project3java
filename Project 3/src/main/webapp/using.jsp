<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="http://localhost:8080/servletIntro/ServletIntro"
		method="get">
		<%
		String selectionText = (String) request.getAttribute("selectionList");
		%>
		Please select a car:<br><%=selectionText%><br> <input
			type="submit" value="Button Doesn't Do Anything..." name="doUsing">
	</form>
</body>
</html>