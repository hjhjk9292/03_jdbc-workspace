package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 공통 템플릿(매번 반복적으로 작성될 코드를 메소드로 정의해둘꺼임)
public class JDBCTemplate {

	// 1. Connection 객체 생성(DB와 접속)한 후 해당 Connection 반환해주는 메소드
	public static Connection getConnection() { // ㅡ 커넥션 받고 싶은 사람 이거 써라~

		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "BOOK", "BOOK"); // url, 계정명, 비번을
																										// conn에 담을거다
			conn.setAutoCommit(false);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;

	}

	// 2. commit을 처리해주는 메소드(남이 주는 Connection 전달받아서 => 전달 받고 싶으면 매개변수에 써라,,,)
	public static void commit(Connection conn) { // static 이라고 작성하면 생성하지 않고도 랜덤처럼 사용 가능... => dao 클래스에 6) 작성
		try {
			if (conn != null && !conn.isClosed()) { // conn이 null이 아니고 conn이 닫혀있지 않을 때
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 3. rollback 처리해주는 메소드(Connection 전달 받아서)
	public static void rollback(Connection conn) { //
		try {
			if (conn != null && !conn.isClosed()) {
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
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 5. Connection 객체 전달 받아서 반납 시켜주는 메소드
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) { // isClosed 는 sql을 throw하고 있으니까 surround catch
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 6. ResultSet 객체 전달 받아서 반납 시켜주는 메소드
	public static void close(ResultSet rset) {
		try {
			if (rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}//
