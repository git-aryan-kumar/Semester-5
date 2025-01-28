package mypackage;

import java.sql.*;

public class Subject {
	
	
	public static void main(String[] args) {
		String DB_URL = "jdbc:mysql://localhost:3306/Subjects";
		String USER = "root";
		String PASS = "";
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_UPDATABLE);
			
			rs = stmt.executeQuery("SELECT * FROM Subject");
			while (rs.next()) {
				System.out.println(rs.getString("Code") + "\t" + rs.getString("Name") + "\t" + rs.getString("Department") + "\t" + rs.getInt("Credits"));
			}
			
			rs.beforeFirst();
			rs = stmt.executeQuery("SELECT * FROM Subject");
			while (rs.next()) {
				if (rs.getString("Name").equals("Java Programming Lab")) {
					rs.updateString("Name", "Advanced Java Lab");
					rs.updateRow();
				}
			}
			
			rs.beforeFirst();
			System.out.println("After Updation");
			rs = stmt.executeQuery("SELECT * FROM Subject");
			while (rs.next()) {
				System.out.println(rs.getString("Code") + "\t" + rs.getString("Name") + "\t" + rs.getString("Department") + "\t" + rs.getInt("Credits"));
			}
			
			String deleteSQL = "DELETE FROM Subject WHERE Name = ?";
			pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setString(1, "System Programming");
			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Row deleted");
			}
			else {
				System.out.println("Not found");
			}
			
			rs.beforeFirst();
			rs = stmt.executeQuery("SELECT * FROM Subject");
			System.out.println("After Deletion");
			while (rs.next()) {
				System.out.println(rs.getString("Code") + "\t" + rs.getString("Name") + "\t" + rs.getString("Department") + "\t" + rs.getInt("Credits"));
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
