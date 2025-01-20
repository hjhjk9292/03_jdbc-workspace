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

// 공통 템플릿(매번 반복적으로 작성될 코드를 메소드로 정의해둘꺼임)
public class JDBCTemplate {
	
	// 모든 메소드 싹 다 static 메소드 => Math.random() 이거랑 비슷함...ㅡ 자바에서 static이 공유폴더
	// 싱클톤패턴 : 메모리 영역에 단 한번만 올려두고 매번 재사용 하는 개념
	
	
	// 1. Connection 객체 생성(DB와 접속)한 후 해당 Connection 반환해주는 메소드
	public static Connection getConnection() { // ㅡ 커넥션 받고 싶은 사람 이거 써라~
		
		/*
		 * 기존의 방식 : jdbc driver, 접속할 db의 url, 접속할 계정명/비밀번호 들을 자바 소스코드 내에 명시적으로 작성함 => 정적코딩방식
		 * 
		 *  > 문제점 : dbms가 변경되었을 경우, 접속할 db의 url 또는 계정명/비밀번호 변경될 경우 => 자바 소스코드를 수정해야됨!
		 *  		=> 수정된 내용을 반영시키고자 한다면 프로그램 재구동 해야됨!!! (프로그램이 비정상적으로 종료됐다가 다시 구동) -> 매끄럽지 않음
		 *  		=> 유지보수에 불편하다!!
		 *  > 해결방식 : db관련된 정보들을 별도로 관리하는 외부파일로(.properties)로 만들어서 관리!
		 *  		  외부파일로부터 읽어들여서 반영 시키면 됨!! => 동적코딩방식
		 * 
		 */
		
		
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
