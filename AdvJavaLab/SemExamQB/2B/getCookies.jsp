<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Get Cookies</title>
</head>
<body>
	<%
	Cookie[] cookies = request.getCookies();
	
	if (cookies != null){
		out.println("<table border = 1><tr><th>Name</th><th>Value</th><th>Expiration Time</th></tr>");
		for (Cookie cookie : cookies){
			out.println("<tr><td>" + cookie.getName() + "</td><td>" + cookie.getValue() + "</td><td>" + cookie.getMaxAge() + "</td></tr>");
		}
		out.println("</table>");
	}
	else{
		System.out.println("No cookies");
	}
	%>
	
	<a href = "index.html">Back to main page</a>
</body>
</html>


