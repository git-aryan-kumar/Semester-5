package mypackage;

import java.sql.*;

public class Department {
	private static final String SERVER_URL = "jdbc:mysql://localhost:3306/";
	private static final String DB_URL = "jdbc:mysql://localhost/Employees";
	private static final String USER = "root";
	private static final String PASS = "";
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
	
		try {
			conn = DriverManager.getConnection(SERVER_URL,USER,PASS);
			stmt = conn.createStatement();
		
			String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS Employees";
			stmt.executeUpdate(createDatabaseSQL);
			System.out.println("Database created if it doesn't already exist. ");
			
			conn.close();
			stmt.close();
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			
			String createTableSQL = "CREATE TABLE IF NOT EXISTS Department( " +
					"Dept_ID INT PRIMARY KEY, " +
					"Name VARCHAR(100), " +
					"Year_Established INT, " +
					"Head_Name VARCHAR(100), " +
					"No_of_Employees INT)";
			stmt.executeUpdate(createTableSQL);
			System.out.println("Create table if it doesn't exist already. ");
			
			String insertDataSQL = "INSERT INTO Department (Dept_ID, Name, Year_Established, Head_Name, No_of_Employees) VALUES " +
					"(1,'CSE',2000,'Naidu',40), " +
					"(2,'ECE',1980,'Singh',25), " +
					"(3,'ISE',2020,'Gowda',30), " +
					"(4, 'AIML',2015,'Shetty',10), " +
					"(5,'AIDS',2018,'Reddy',15)";
			stmt.executeUpdate(insertDataSQL);
			System.out.println("5 records inserted into table. ");
			
			String displayAll = "SELECT * FROM Department";
			ResultSet rsAll = stmt.executeQuery(displayAll);
			System.out.println("All employee records");
			System.out.println("Dept_ID\tName\tYear_Established\tHead_Name\tNo_of_Employees");
			while (rsAll.next()) {
				System.out.println(rsAll.getInt("Dept_ID") + "\t" + rsAll.getString("Name") + "\t" + rsAll.getInt("Year_Established") + "\t\t\t" + rsAll.getString("Head_Name") + "\t\t" + rsAll.getInt("No_of_Employees"));
			}
			
			String displayYear = "SELECT * FROM Department WHERE Year_Established = ?";
			pstmt = conn.prepareStatement(displayYear);
			pstmt.setInt(1, 2000);
			ResultSet rsYear = pstmt.executeQuery();
			System.out.println("Dept_ID\tName\tYear_Established\tHead_Name\tNo_of_Employees");
			if (rsYear.next()) {
				System.out.println(rsYear.getInt("Dept_ID") + "\t" + rsYear.getString("Name") + "\t" + rsYear.getInt("Year_Established") + "\t\t\t" + rsYear.getString("Head_Name") + "\t\t" + rsYear.getInt("No_of_Employees"));
			}
			
			System.out.println("Record obtained after entering Dept_ID and Name: ");
			String displayDept = "SELECT * FROM Department WHERE Dept_ID = ? and Name = ?";
			pstmt = conn.prepareStatement(displayDept);
			pstmt.setInt(1, 2);
			pstmt.setString(2, "ECE");
			ResultSet rsDept = pstmt.executeQuery();
			System.out.println("Dept_ID\tName\tYear_Established\tHead_Name\tNo_of_Employees");
			if (rsDept.next()) {
				System.out.println(rsDept.getInt("Dept_ID") + "\t" + rsDept.getString("Name") + "\t" + rsDept.getInt("Year_Established") + "\t\t\t" + rsDept.getString("Head_Name") + "\t\t" + rsDept.getInt("No_of_Employees"));
			}
			
			System.out.println("Inserting new row using Prepared Statement: ");
			String insertNewRow = "INSERT INTO Department (Dept_ID, Name, Year_Established, Head_Name, No_of_Employees) VALUES (?,?,?,?,?)";
			pstmt = conn.prepareStatement(insertNewRow);
			pstmt.setInt(1, 6);
			pstmt.setString(2, "EEE");
			pstmt.setInt(3, 1990);
			pstmt.setString(4, "Kumar");
			pstmt.setInt(5, 20);
			pstmt.executeUpdate();
			System.out.println("New Row Inserted");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
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
