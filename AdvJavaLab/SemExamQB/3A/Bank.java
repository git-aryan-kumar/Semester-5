package mypackage;

import java.sql.*;

public class Bank {
	private static final String SERVER_URL = "jdbc:mysql://localhost:3306/";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/Banks";
	private static final String USER = "root";
	private static final String PASS = "";
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(SERVER_URL,USER,PASS);
			stmt = conn.createStatement();
			
			String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS Banks";
			stmt.executeUpdate(createDatabaseSQL);
			System.out.println("Database created");
			
			conn.close();
			stmt.close();
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			conn.setAutoCommit(false);
			String createTableSQL = "CREATE TABLE IF NOT EXISTS Bank( " +
					"Account_No INT PRIMARY KEY, " +
					"Account_Name VARCHAR(100), " +
					"Type_of_Account VARCHAR(100), " +
					"Balance INT)";
			stmt.executeUpdate(createTableSQL);
			System.out.println("Table created");
			
			
			String insertTableSQL = "INSERT INTO Bank (Account_No, Account_Name, Type_of_Account, Balance) VALUES " +
					"(1,'Aryan','Savings',20000), " +
					"(2,'Basava','Current',18000), " +
					"(3,'Hari','Savings',22000), " +
					"(4,'Reddy','Savings',15000), " +
					"(5,'Shetty','Current',10000)";
			stmt.executeUpdate(insertTableSQL);
			System.out.println("Inserted records into table");
			
			conn.commit();
			System.out.println("Commited records");
			
			Savepoint savepoint1 = conn.setSavepoint();
			
			String display = "SELECT * FROM Bank";
			ResultSet rs = stmt.executeQuery(display);
			System.out.println("Bank Records: ");
			while (rs.next()) {
				System.out.println(rs.getInt("Account_No") + "\t" + rs.getString("Account_Name") + "\t" + rs.getString("Type_of_Account") + "\t" + rs.getInt("Balance"));
			}
			
			String insertTablePrepare = "INSERT INTO Bank (Account_No, Account_Name, Type_of_Account, Balance) VALUES (?,?,?,?)";
			pstmt = conn.prepareStatement(insertTablePrepare);
			pstmt.setInt(1, 6);
			pstmt.setString(2,"Bathini");
			pstmt.setString(3, "Current");
			pstmt.setInt(4, 25000);
			pstmt.executeUpdate();
			
			try {
				String insertTablePrepare2 = "INSERT INTO Bank (Account_No, Account_Name, Type_of_Account, Balance) VALUES (?,?,?,?)"; //Duplicate entry. Invalid as Account_No is primary key
				pstmt = conn.prepareStatement(insertTablePrepare2);
				pstmt.setInt(1, 6);
				pstmt.setString(2,"Aniket");
				pstmt.setString(3, "Current");
				pstmt.setInt(4, 25000);
				pstmt.executeUpdate();
				conn.commit();
			}
			catch (Exception e) {
				conn.rollback(savepoint1);
				System.out.println("Rollback");
			}
			
			 pstmt.setInt(1, 7);
	            pstmt.setString(2, "Aniket Bhai");
	            pstmt.setString(3, "Savings");
	            pstmt.setInt(4, 30000);
	            pstmt.executeUpdate();
	            System.out.println("Inserted new valid record (John)");
			
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
