package util;

import java.sql.*;

public class DbUtil {
	private static Connection conn = null;

	public static Connection getConnection(){
		
		try{
			String username = "root";
			String passwd = "";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/UserInfo",username,passwd);
		} catch(Exception e) {
			e.getMessage();
		}
		
		return conn;
	}
	
	public static void closeConnection(Connection conn){
		
		try{
			conn.close();
		} catch(Exception e){
			e.getMessage();
		}
	}
    
}
