package mypackage;

import java.sql.*;

public class Registration {
	private static final String SERVER_URL = "jdbc:mysql://localhost:3306/";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/Registration";
	private static final String USER = "root";
	private static final String PASS = "";
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(SERVER_URL,USER,PASS);
			stmt = conn.createStatement();
			
			String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS Registration";
			stmt.executeUpdate(createDatabaseSQL);
			System.out.println("Database created");
			
			conn.close();
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String createTableSQL = "CREATE TABLE IF NOT EXISTS Student( " +
					"Roll INT, " +
					"Name VARCHAR(100), " +
					"Program VARCHAR(100), " +
					"Year INT)";
			stmt.executeUpdate(createTableSQL);
			System.out.println("Table created");
			
			
			String display1 = "SELECT * FROM Student WHERE Year = 2023";
			ResultSet rs1 = stmt.executeQuery(display1) ;
			System.out.println();
			System.out.println();
			while (rs1.next()) {
				System.out.println(rs1.getInt("Roll") + "\t" + rs1.getString("Name") + "\t" + rs1.getString("Program") + "\t" + rs1.getInt("Year"));
			}
			
			System.out.println();
			System.out.println();
			String display2 = "SELECT * FROM Student WHERE Program = 'BE'";
			ResultSet rs2 = stmt.executeQuery(display2) ;
			while (rs2.next()) {
				System.out.println(rs2.getInt("Roll") + "\t" + rs2.getString("Name") + "\t" + rs2.getString("Program") + "\t" + rs2.getInt("Year"));
			}
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
