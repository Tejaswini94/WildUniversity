package com.example.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static Properties dbProperties;
	private static String url;
	private static String username;
	private static String password;
	private static Connection conn;
	
	//Connection Pool 
	static {
		try {
			
			 dbProperties = new Properties();
			 dbProperties.load(new FileInputStream("C:\\PropertiesFile\\jdbc.properties"));
			
			// Loading the database driver
			Class.forName(dbProperties.getProperty("driver"));
			// URL for connecting to db
			url = dbProperties.getProperty("url"); //This will return the value of url, if not found return null
			// username and password for connecting to db
			username = dbProperties.getProperty("username");
			password = dbProperties.getProperty("password");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	//Getting connection
	public static Connection getConnection(){

		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	// closing connection
	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
