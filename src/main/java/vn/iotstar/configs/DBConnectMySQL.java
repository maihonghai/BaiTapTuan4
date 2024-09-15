package vn.iotstar.configs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnectMySQL {
	private static String DB_URL = "jdbc:mysql://localhost:3306/ltweb";
	private static String USER_NAME = "root";
	private static String PASSWORD = "haii1001";
	private static Connection conn;
	
	public static Connection getConnection() throws IOException {
		conn = null;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = ((Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD));
			
		}catch(SQLException ex) {
			Logger.getLogger(DBConnectMySQL.class.getName()).log(Level.SEVERE, null,ex);
		}
		return (conn);
	}
	
	public static void main(String[] args) {
		try {
			Connection c = getConnection();
			System.out.println(DBConnectMySQL.getConnection());
		if(c == null) {
			System.out.println("something wrong");
			
		}else {
			System.out.println("OK");
		}
		}
		catch(Exception e) {e.printStackTrace();}
	}
}
