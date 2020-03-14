package com.ss.project2.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/library?useSSL=false";
	private String username = "Brendan";
	private String password = "Sequel123657!";
	
	protected Connection getConnection()  {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(false);
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
