package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connect {
	public static Connection conn; // Connection : 데이터베이스에 접근하게 해주는 하나의 객체 
	public static PreparedStatement pstmt;
	public static ResultSet rs; // ResultSet : 어떠한 정보를 담을 수 있는 객체 
	
	public static Connection connect() {
		try {
	        Class.forName("org.sqlite.JDBC");
	        conn = DriverManager.getConnection("jdbc:sqlite:chatting.db");
	      } catch ( Exception e ) {
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	        System.exit(0);
	      }
	      //System.out.println("Opened DB successfully");
		return conn;
	}
}
