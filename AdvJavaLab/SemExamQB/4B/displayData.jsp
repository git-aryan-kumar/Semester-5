<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import = "java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Retrieval</title>
</head>
<body>
	<%
	String DB_URL = "jdbc:mysql://localhost:3306/Students";
	String USER = "root";
	String PASS = "";
	
	String usn = request.getParameter("usn");
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
		
		String query = "SELECT * FROM Student WHERE usn = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, usn);
		rs = pstmt.executeQuery();
		
		if (rs.next()){
			out.println("<table border = 1><tr><th>Name</th><th>USN</th><th>Department</th></tr>");
			out.println("<tr><td>" + rs.getString("name") + "</td><td>" + rs.getString("usn") + "</td><td>" + rs.getString("department") + "</td></tr></table>");
		}
		else{
			out.println("<p>Invalid usn</p>");
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
