package com.app.mobicrop;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	static Connection conn=null;
	public static  Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobicrop", "root", "ROOT");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return conn;
	}
}
