package electricityict;

import java.sql.*;

public class DBHandler {
	
//	public static void main(String[] args) {
//		establishConnection();
//	}
	
	public static  Connection establishConnection() {
		
		String url = "jdbc:mysql://localhost:3310/EBill";
		String user = "root";
		String password = "Kallol9836@";
		Connection connection=null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			connection =DriverManager.getConnection(url,user,password);
			System.out.println("Database connected successfully!");}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}