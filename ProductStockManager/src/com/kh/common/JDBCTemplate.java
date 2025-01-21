package com.kh.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	public static Connection getConnection() {
		
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			
			prop.load(new FileInputStream("resources/driver.properties")); // prop.load(입력용스트림생성구문()); // 2번째꺼 클릭
			
			Class.forName(prop.getProperty("driver")); // 외부파일을 읽어들이는 방식으로 변경해서 작성
			conn = DriverManager.getConnection(prop.getProperty("url"),
												prop.getProperty("username"),
												prop.getProperty("password")); // url, 계정명, 비번을 conn에 담을거다
			conn.setAutoCommit(false);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	// 2. commit을 처리해주는 메소드(남이 주는 Connection 전달받아서 => 전달 받고 싶으면 매개변수에 써라,,,)
	public static void commit(Connection conn) { // static 이라고 작성하면 생성하지 않고도 랜덤처럼 사용 가능... => dao 클래스에 6) 작성
		try {
			if(conn != null && !conn.isClosed()) { // conn이 null이 아니고 conn이 닫혀있지 않을 때
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3. rollback 처리해주는 메소드(Connection 전달 받아서)
	public static void rollback(Connection conn) { // 
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// JDBC용 객체들 전달 받아서 반납처리해주는 메소드
	// 4. Statement 관련 객체 전달 받아서 반납시켜주는 메소드
	public static void close(Statement stmt) { // 다형성 적용 (Statement가 부모니까, PreparedStatement 안 만들어도 됨)
		try {
			if(stmt != null && !stmt.isClosed()){
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// 5. Connection 객체 전달 받아서 반납 시켜주는 메소드
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) { //isClosed 는 sql을 throw하고 있으니까 surround catch 
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// 6. ResultSet 객체 전달 받아서 반납 시켜주는 메소드
	public static void close(ResultSet rset) {
		try {
			if(rset !=null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}//