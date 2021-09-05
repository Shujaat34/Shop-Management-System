package com.bukhari.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBConnection {
	
	static Connection con = null;
	public static Connection getConnection() {
		try {
			if (con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopproject","root","");
		}
		}catch(SQLException e) {
			System.out.println("SQLEXCEPTTION in LoginBean Class");
		}catch(ClassNotFoundException e) {
			System.out.println("Class not Found in LoginBean Class");
			
		}
		return con;
	}

}
