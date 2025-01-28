<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String name = request.getParameter("name");
	Integer age = Integer.parseInt(request.getParameter("age"));
	Double price;
	
	if (age > 62){
		price = 50.0;
	}
	else if (age < 10){
		price = 30.0;
	}
	else{
		price = 80.0;
	}
	%>
	<p>Name: <%=name %></p>
	<p>Age: <%=age%></p>
	<p>Ticket Price is: <%=price %></p>
</body>
</html>
