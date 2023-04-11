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
		String user = (String) request.getAttribute("username");
		String password = (String) request.getAttribute("password");
		out.println("Hello " + user);
		request.setAttribute("username", user);
		request.setAttribute("password", password);
		if(request.getAttribute("instance")!=null){
			request.setAttribute("instance", request.getAttribute("instance"));
		}
		%>
		<br>
		<%
		out.println("\nPlease do prediction stuff");
		%>
	</h3>
	<form action="http://localhost:8085/Project_3/DataServlet"
		method="post" name="selection">
		<%
		//String selectionText = (String) request.getAttribute("selectionList");
		String outlooks = (String) request.getAttribute("outlooks");
		String humidity = (String) request.getAttribute("humidity");
		String temperature = (String) request.getAttribute("temperature");
		String windy = (String) request.getAttribute("windy");
		Integer size = (Integer) request.getAttribute("size");
		%>
		Current size: <%=size%><br>
		Please select the outlook:<br><%=outlooks%><br>
		<%=humidity%><br><%=temperature%><br><%=windy%>
		<input type="submit" value="Predict" name="doUsing">
		<input type="submit" value="Delete Random (I tried to do most similar but couldn't)" name="doUsing2">
	</form>
</body>
</html>