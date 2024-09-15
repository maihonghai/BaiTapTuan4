package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.DatabaseMetaData; 


public class DBConnectSQL {
	private final String serverName = "LAPTOP-QTEB4KQ5\\SQLEXPRESS";
	private final String dbName = "ltweb";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "";
	private final String password = "";
	
	
	// Kết nối SQL Server với Windows Authentication
	public Connection getConnection() throws Exception {
		
		   String url = "jdbc:sqlserver://" + serverName + ":" + portNumber+ "\\" +instance + ";integratedSecurity = true;databaseName=" + dbName;
		   if (instance == null || instance.trim().isEmpty())
				url ="jdbc:sqlserver://" + serverName + ":" + portNumber +";integratedSecurity = true;databaseName=" + dbName;
		   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		   return DriverManager.getConnection(url,userID, password);
		   	
	}
	
	//Test ct
	public static void main(String[] args) {
	try {
		System.out.println(new DBConnectSQL().getConnection());
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}
