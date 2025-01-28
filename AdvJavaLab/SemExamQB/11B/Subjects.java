package mypackage;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.sql.*;

@WebServlet("/Subjects")
public class Subjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Subject";
    private static final String USER = "root";
    private static final String PASS = "";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer Faculty_ID = Integer.parseInt(request.getParameter("Faculty_ID"));
		Integer Sub_ID = Integer.parseInt(request.getParameter("Sub_ID"));
		String Sub_Name = request.getParameter("Sub_Name");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			
			String displayAll = "SELECT * FROM Subjects WHERE Faculty_ID = 1";
			ResultSet rs = stmt.executeQuery(displayAll);
			out.println("<table border = 1><tr><th>Subject ID</th><th>Subject Name</th><th>Faculty ID</th></tr>");
			while (rs.next()) {
				out.println("<tr><td>" + rs.getInt("Sub_ID") + "</td><td>" + rs.getString("Sub_Name") + "</td><td>" + rs.getInt("Faculty_ID") + "</td></tr>"); 
			}
			out.println("</table>");
			
			String displayAll2 = "SELECT * FROM Subjects WHERE Faculty_ID = 2";
			ResultSet rs2 = stmt.executeQuery(displayAll2);
			out.println("<table border = 1><tr><th>Subject ID</th><th>Subject Name</th><th>Faculty ID</th></tr>");
			while (rs2.next()) {
				out.println("<tr><td>" + rs2.getInt("Sub_ID") + "</td><td>" + rs2.getString("Sub_Name") + "</td><td>" + rs2.getInt("Faculty_ID") + "</td></tr>"); 
			}
			out.println("</table>");
			
			String displayAll3 = "SELECT * FROM Subjects WHERE Faculty_ID = 3";
			ResultSet rs3 = stmt.executeQuery(displayAll3);
			out.println("<table border = 1><tr><th>Subject ID</th><th>Subject Name</th><th>Faculty ID</th></tr>");
			while (rs3.next()) {
				out.println("<tr><td>" + rs3.getInt("Sub_ID") + "</td><td>" + rs3.getString("Sub_Name") + "</td><td>" + rs3.getInt("Faculty_ID") + "</td></tr>"); 
			}
			out.println("</table>");
			
			String updateSQL = "UPDATE Subjects SET Sub_Name = ?,Sub_ID = ? WHERE Faculty_ID = ?";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, Sub_Name);
			pstmt.setInt(2, Sub_ID);
			pstmt.setInt(3, Faculty_ID);
			int rows = pstmt.executeUpdate();
			
			if (rows > 0) {
				out.println("<p>Number of rows: " + rows + "</p>");
			}
			else {
				out.println("<p>No Updates</p>");
			}
			
			String displayAll5 = "SELECT * FROM Subjects";
			ResultSet rs5 = stmt.executeQuery(displayAll5);
			out.println("<table border = 1><tr><th>Subject ID</th><th>Subject Name</th><th>Faculty ID</th></tr>");
			while (rs5.next()) {
				out.println("<tr><td>" + rs5.getInt("Sub_ID") + "</td><td>" + rs5.getString("Sub_Name") + "</td><td>" + rs5.getInt("Faculty_ID") + "</td></tr>"); 
			}
			out.println("</table>");
			
			
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
