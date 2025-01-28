package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Grades {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/College";
	private static final String USER = "root";
	private static final String PASS = "";
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_UPDATABLE);
			String query = "SELECT * FROM Student";
			rs = stmt.executeQuery(query);
			
			System.out.println("All records:");
			while (rs.next()) {
				System.out.println(rs.getString("Name") + "\t" + rs.getInt("USN") + "\t" + rs.getString("Department") + "\t" + rs.getFloat("CGPA"));
			}
			
			System.out.println("Student who have less than 9 CGPA:");
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (rs.getFloat("CGPA") < 9) {
					System.out.println(rs.getString("Name") + "\t" + rs.getInt("USN") + "\t" + rs.getString("Department") + "\t" + rs.getFloat("CGPA"));
				}
			}
			
			System.out.println("Table after updating is: ");
			rs.beforeFirst();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (rs.getString("Name").equals("John")) {
					rs.updateFloat("CGPA", 9.4f);
					rs.updateRow();
				}
			}
			
			rs.beforeFirst();
			System.out.println("All records:");
			while (rs.next()) {
				System.out.println(rs.getString("Name") + "\t" + rs.getInt("USN") + "\t" + rs.getString("Department") + "\t" + rs.getFloat("CGPA"));
			}
			
		}
		catch(SQLException e) {
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
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
