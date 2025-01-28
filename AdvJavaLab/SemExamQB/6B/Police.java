package mypackage;

import jakarta.servlet.annotation.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;


@WebServlet("/Police")
public class Police extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/Polices";
	private static final String USER = "root";
	private static final String PASS = "";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Integer phone = Integer.parseInt(request.getParameter("phone"));
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM Police WHERE phone = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, phone);
			ResultSet rs = pstmt.executeQuery();
			out.println("<table border = 1><tr><th>Phone Number</th><th>Area</th></tr>");
			while (rs.next()) {
				out.println("<tr><td>" + rs.getInt("phone") + "</td><td>" + rs.getString("area") + "</td></tr>");
			}
			out.println("</table>");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
