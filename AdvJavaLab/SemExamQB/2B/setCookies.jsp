<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Set Cookies</title>
</head>
<body>
	<%
	Cookie cookie1 = new Cookie("cookie1","value1");
	Cookie cookie2 = new Cookie("cookie2","value2");
	Cookie cookie3 = new Cookie("cookie3","value3");
	Cookie cookie4 = new Cookie("cookie4","value4");
	
	cookie1.setMaxAge(60);
	cookie2.setMaxAge(30);
	cookie3.setMaxAge(20);
	cookie4.setMaxAge(10);
	
	response.addCookie(cookie1);
	response.addCookie(cookie2);
	response.addCookie(cookie3);
	response.addCookie(cookie4);
	%>
	
	<a href = "index.html">Back to main page</a>
</body>
</html>
