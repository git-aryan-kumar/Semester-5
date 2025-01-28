<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book storage</title>
</head>
<body>
	<%
	String DB_URL = "jdbc:mysql://localhost:3306/Books";
	String USER = "root";
	String PASS = "";
	
	Integer Book_No = Integer.parseInt(request.getParameter("Book_No"));
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
		
		String query = "SELECT * FROM Book WHERE Book_No = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, Book_No);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()){
			out.println("<table border = 1><tr><th>Book_No</th><th>Title</th><th>Author</th><th>Publication</th><th>Price</th></tr>");
			out.println("<td>" + rs.getInt("Book_No") + "</td><td>" + rs.getString("Title") + "</td><td>" + rs.getString("Author") + "</td><td>" + rs.getString("Publication") + "</td><td>" + rs.getInt("Price") + "</td></tr></table>");
		}
	}
	catch (ClassNotFoundException e){
		e.printStackTrace();
	}
	catch (SQLException e){
		e.printStackTrace();
	}
	finally{
		try{
			if (conn != null){
				conn.close();
			}
			if (stmt != null){
				stmt.close();
			}
			if (pstmt != null){
				pstmt.close();
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	%>
</body>
</html>
