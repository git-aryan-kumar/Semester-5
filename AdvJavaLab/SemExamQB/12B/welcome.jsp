<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
	<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	
	session.setAttribute("username",username);
	session.setAttribute("password",password);
	
	if ("Admin".equals(username) && "Password".equals(password)){
		out.println("<h2>Welcome " + username + "</h2>");
	}
	else{
		out.println("<h2>Incorrect username or password</h2>");
	}
	%>
</body>
</html>
