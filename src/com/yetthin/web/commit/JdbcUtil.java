package com.yetthin.web.commit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class JdbcUtil {
	 
	
	
	public static  Connection getconnection() {
		Connection driver=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		try {
			driver = DriverManager.getConnection("jdbc:mysql://localhost:3306/yetthin?useUnicode=true&amp;characterEncoding=utf8&zeroDateTimeBehavior = convertToNull", "yetthindb", "yetthindb2016");
		} catch (SQLException e) {
		 
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return driver;
	}
	public static void close(Connection con ,PreparedStatement pre ){
		try {
			pre.close();
			con.close();
			pre=null;
			con=null;
		} catch (SQLException e) {
			 
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
