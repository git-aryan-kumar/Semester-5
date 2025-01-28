package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Country {
	private static final String SERVER_URL = "jdbc:mysql://localhost:3306/";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/Countries";
	private static final String USER = "root";
	private static final String PASS = "";
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(SERVER_URL,USER,PASS);
			stmt = conn.createStatement();
			
			String createDatabase = "CREATE DATABASE IF NOT EXISTS Countries";
			stmt.executeUpdate(createDatabase);
			System.out.println("Database created if it doesn't already exist");
			
			conn.close();
			stmt.close();
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			
			String createTableSQL = "CREATE TABLE IF NOT EXISTS Country( " +
					"Country_Code VARCHAR(100), " +
					"Capital VARCHAR(100), " +
					"Continent VARCHAR(100), " +
					"Population INT)";
			stmt.executeUpdate(createTableSQL);
			System.out.println("Table created if it doesn't already exist");
			
			String insertTableSQL = "INSERT INTO Country (Country_Code, Capital, Continent, Population) VALUES " +
					"('IN','New Delhi','Asia',20000000), " +
					"('AU','Sydney','Oceania',100000), " +
					"('US','Washington','North America',1500000), " +
					"('BR','Brasilia','South America',120000), " +
					"('SA','Cape Town','Africa',250000)";
			stmt.executeUpdate(insertTableSQL);
			System.out.println("Records inserted into table");
			
			String queryAsc = "SELECT * FROM Country ORDER BY Population ASC";
			ResultSet rs = stmt.executeQuery(queryAsc);
			System.out.println("Records in ascending order of population are: ");
			while (rs.next()) {
				System.out.println(rs.getString("Country_Code") + "\t" + rs.getString("Capital") + "\t" + rs.getString("Continent") + "\t" + rs.getInt("Population"));
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
