package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student {
	private static final String SERVER_URL = "jdbc:mysql://localhost:3306/";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/Student";
	private static final String USER = "root";
	private static final String PASS = "";
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(SERVER_URL,USER,PASS);
			stmt = conn.createStatement();
			
			String query = "CREATE DATABASE IF NOT EXISTS Student";
			stmt.executeUpdate(query);
			System.out.println("Database created");
			
			conn.close();
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			
			String createTableSQL = "CREATE TABLE IF NOT EXISTS Students(" +
					"Name VARCHAR(100), " +
					"USN VARCHAR(100), " +
					"Sem INT, " +
					"Course VARCHAR(100), " +
					"Grade VARCHAR(100))";
			stmt.executeUpdate(createTableSQL);
			System.out.println("Database and table created");
			
			String insertTableSQL = "INSERT INTO Students (Name, USN, Sem, Course, Grade) VALUES (?,?,?,?,?)";
			pstmt = conn.prepareStatement(insertTableSQL);
			pstmt.setString(1, "Ram");
			pstmt.setString(2, "1MS22CS001");
			pstmt.setInt(3, 5);
			pstmt.setString(4, "Advanced Java");
			pstmt.setString(5, "A");
			pstmt.executeUpdate();
			
			String display = "SELECT * FROM Students";
			rs = stmt.executeQuery(display);
			while (rs.next()) {
				System.out.println(rs.getString("Name") + "\t" + rs.getString("USN") + "\t" + rs.getInt("Sem") + "\t" + rs.getString("Course") + "\t" + rs.getString("Grade"));
			}
			
			String changeGradeSQL = "UPDATE Students SET Grade = 'S' WHERE Name = 'Ram'";
			stmt.executeUpdate(changeGradeSQL);
			
			String display1 = "SELECT * FROM Students";
			rs = stmt.executeQuery(display1);
			while (rs.next()) {
				System.out.println(rs.getString("Name") + "\t" + rs.getString("USN") + "\t" + rs.getInt("Sem") + "\t" + rs.getString("Course") + "\t" + rs.getString("Grade"));
			}
			
		}
		catch (SQLException e) {
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
