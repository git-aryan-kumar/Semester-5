package mypackage;

import java.sql.*;

public class Movie {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/Movie";
	private static final String USER = "root";
	private static final String PASS = "";
	
	public static void main(String[] args) {	
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT * FROM Movies";
			rs = stmt.executeQuery(query);
			System.out.println("All records are: ");
			while (rs.next()) {
				System.out.println(rs.getInt("ID") + "\t" + rs.getString("Movie_Name") + "\t" + rs.getString("Genre") + "\t" + rs.getFloat("IMDB_Rating") + "\t" + rs.getInt("Year"));
			}
			
			rs.absolute(4);
			System.out.println("5th record is: ");
			if (rs.next()) {
				System.out.println(rs.getInt("ID") + "\t" + rs.getString("Movie_Name") + "\t" + rs.getString("Genre") + "\t" + rs.getFloat("IMDB_Rating") + "\t" + rs.getInt("Year"));
			}
			
			String insertSQL = "INSERT INTO Movies (ID, Movie_Name, Genre, IMDB_Rating, Year) VALUES (?,?,?,?,?)";
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setInt(1, 18);
			pstmt.setString(2, "Solo");
			pstmt.setString(3, "Sci-Fi");
			pstmt.setFloat(4, 8.5f);
			pstmt.setInt(5, 2018);
			pstmt.executeUpdate();
			System.out.println("Inserted record");
			while (rs.next()) {
				System.out.println(rs.getInt("ID") + "\t" + rs.getString("Movie_Name") + "\t" + rs.getString("Genre") + "\t" + rs.getFloat("IMDB_Rating") + "\t" + rs.getInt("Year"));
			}
			
			rs.beforeFirst();
			while (rs.next()) {
				if (rs.getFloat("IMDB_Rating") < 8.5) {
					rs.deleteRow();
				}
			}
			System.out.println("Deleted row");
			
			System.out.println("After deleting records are: ");
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getInt("ID") + "\t" + rs.getString("Movie_Name") + "\t" + rs.getString("Genre") + "\t" + rs.getFloat("IMDB_Rating") + "\t" + rs.getInt("Year"));
			}
			
			
			rs = stmt.executeQuery(query);
			rs.beforeFirst();
			while (rs.next()) {
				if (rs.getInt("ID") == 10){
					rs.updateString("Genre", "Sci - Fi Update");
					rs.updateRow();
				}
			}
			System.out.println("Updated row. ");
			
			System.out.println("After updating");
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getInt("ID") + "\t" + rs.getString("Movie_Name") + "\t" + rs.getString("Genre") + "\t" + rs.getFloat("IMDB_Rating") + "\t" + rs.getInt("Year"));
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
