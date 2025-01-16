package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Member;

// DAO(Data Access Object) : DB와 직접적으로 접근해서 사용자의 요청에 맞는 SQL문 실행 후 결과 받기(JDBC)
//							 결과를 Controller로 다시 리턴
public class MemberDao {
	
	/*
	 * * Statement와 PreparedStatement의 특징 ㅡ> 둘 다 쿼리를 돌리는 객체, 부모(Statement)보다 자식(PreparedStatement)의 성능이 더 좋다
	 * - 둘 다 sql문을 실행하고 결과를 받아내는 객체(둘 중 하나를 이용해서 sql문 실행하면됨)
	 * - Statement가 PreparedStatement의 부모(상속구조)
	 * 
	 * * Statement와 PreparedStatement의 차이점
	 * - Statement 같은 경우 sql문을 바로 전달하면서 실행시키는 객체
	 * 		(즉, sql문을 "완성"형태로 만들어 둬야됨!! == 사용자가 입력한 값이 다 채워진 형태로!)
	 * 
	 * 		> 기존의 Statement 방식
	 * 		1) Connection 객체를 통해 Statement 객체 생성 : 			stmt = conn.createStatement();
	 * 		2) Statement 객체를 통해 "완성된 sql" 실행 및 결과 받기 : 	결과 = stmt.executeXXXX(완성된sql);
	 * 
	 * 
	 * - PreparedStatement 같은 경우 "미완성된 sql문"을 잠시 보관해둘 수 있는 객체
	 * 		(즉, 사용자가 입력한 값들을 채워두지 않고 각각 들어갈 공간을 확보만 미리 해놔도 됨!!)
	 * 		 단, 해당 sql문 본격적으로 실행하기 전에는 빈 공간을 사용자가 입력한 값으로 채워서 실행하긴 해야됨
	 * 
	 * 		> PreparedStatement 방식
	 * 		1) Connection 객체를 통해 PreparedStatement 객체 생성 : 	pstmt = conn.preparedStatement([미완성된]sql문);
	 * 		2) pstmt에 담긴 sql문이 미완성 상태일 경우 우선은 완성시켜야됨 : 	pstmt.setXXX(1, "대체할 값");
	 * 														  	pstmt.setXXX(2, "대체할 값");
	 * 		3) 해당 완성된 sql문 실행 및 결과 받기					: 	결과 = pstmt.executeXXXX();
	 * 
	 */
	
	public int insertMember(Member m) {
		// insert문 => 처리된 행수 => 트랜젝션 처리
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// 실행할 sql문 (미완성된 형태로 둘 수 있음)
		// INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, 'XXXX', 'XXXX', 'XXX', 'X', XX, 'XXXXXXXXX', 'XXXXXXXXXXX', 'XX', 'XXXX', SYSDATE);
		// 미리 사용자가 입력한 값들이 들어갈 수 있게 공간 확보(? == 홀더)만 해두면 됨!
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver"); // oracle.jdbc.driver. 패키지에 있는 OracleDriver 클래스
			
			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
			// 3) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);	// 애초에 PreparedStatement 객체 생성 시 sql문을 담은 채로 생성! (하필 미완성된 sql문임!)

			// > ? 공간을 실제 값(사용자가 입력한 값)으로 채워준 후 실행
			// pstmt.setString(홀더순번, 대체할값);			=> '대체할값' (양 옆에 홑따옴표 감싸준 상태로 알아서 들어감)
			// pstmt.setInt(홀더순번, 대체할값);				=> 대체할값
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());	// 마지막에 쓴 숫자 9와 물음표 개수가 같아야함
			
			// 4, 5) sql문 실행 및 결과 받기
			result = pstmt.executeUpdate();	// 기존 statement 에서는 stmt.executeUpdate(sql);
			
			// 6) 트랜젝션 처리
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
	}
	
	
	
	public ArrayList<Member> selectList() {
		// select문(여러행) => ResultSet객체 => ArrayList<Member> 객체 	ㅡ> 표 형식으로 받아야하니까
		
		ArrayList<Member> list = new ArrayList<Member>(); // [] 텅 빈 리스트
		
		Connection conn = null; // 연결해주는 Connection 객체 null로 초기화
		PreparedStatement pstmt = null; //
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER"; // 애초에 완성된 SQL문
		
		try {
			// 1)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC"); // 이런 url로 jdbc계정에 접속해서 conn에 넣어줌
			// 3)
			pstmt = conn.prepareStatement(sql); // 애초에 완성된 sql문을 담았음! => 곧바로 실행	ㅡ> ? 또는 구멍 없으니까
			// 4, 5)
			rset = pstmt.executeQuery(); // rset에 조회된 표와 같은 데이터가 담겨있을 것임
			// 6)
			while(rset.next()) {
				// 현재 rset이 참조하고 있는 해당 행의 모든 컬럼값 뽑아서 => 한 Member 객체에 담기 => 리스트에 차곡차곡 추가		ㅡ> 오라클에서 커서 행마다 바뀌며 뽑아지는 거
				list.add(new Member(rset.getInt("userno"),
									rset.getString("userid"),
									rset.getString("userpwd"),
									rset.getString("username"),
									rset.getString("gender"),
									rset.getInt("age"),
									rset.getString("email"),
									rset.getString("phone"),
									rset.getString("address"),
									rset.getString("hobby"),
									rset.getDate("enroll_date")
									));
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list; // list에 담은 값을 반환
		
	}
	
	
	
	public Member selectByUserId(String userId) {
		// select문(한 행) => ResultSet 객체 => Member 객체
		Member m = null; // m 이라는 객체는 초기화
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null; // select 수행 결과 초기화
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // jdbc 가서.. class 접근
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC"); // connection 객체 만들고 (url,계정명,비번)
			
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery(); // 쿼리가 돌아가고 표처럼 나오는 한줄의 데이터가 rset에 담김
			
			if(rset.next()) {
				
				m = new Member(rset.getInt("userno"),
								rset.getString("userid"),
								rset.getString("userpwd"),
								rset.getString("username"),
								rset.getString("gender"),
								rset.getInt("age"),
								rset.getString("email"),
								rset.getString("phone"),
								rset.getString("address"),
								rset.getString("hobby"),
								rset.getDate("enroll_date")
							   );
				
			}
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return m;
	}
	
	
	
	
//;
	
	public ArrayList<Member> selectByUserName(String keyword) {
		// select문 (여러행) => ResultSet 객체 => ArrayList<Member> 차곡차곡 담기
		
		ArrayList<Member> list = new ArrayList<Member>(); // []
		
		Connection conn = null;		
		PreparedStatement pstmt = null;	
		ResultSet rset = null;
		
		// String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%?%'";
		// pstmt.setString(1, "관"); // '대체할값'
		// SELECT * FROM MEMBER WHERE USERNAME LIKE '%'관'%';
		
		// 해결방법1.
//		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%' || ? || '%'";
		
		// 해결방법2.
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			pstmt = conn.prepareStatement(sql); // 미완성된 sql
			
			// 해결방법1의 sql문 일 경우
//			pstmt.setString(1, keyword);// SELECT * FROM MEMBER WHERE USERNAME LIKE '%' || 관 || '%'";
			
			// 해결방법2의 sql문 일 경우
			pstmt.setString(1, "%" + keyword + "%"); // SELECT * FROM MEMBER WHERE USERNAME LIKE '%관%'

			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Member(rset.getInt("userno"),
									rset.getString("userid"),
									rset.getString("userpwd"),
									rset.getString("username"),
									rset.getString("gender"),
									rset.getInt("age"),
									rset.getString("email"),
									rset.getString("phone"),
									rset.getString("address"),
									rset.getString("hobby"),
									rset.getDate("enroll_date")
									));
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return list;
				
	}
	
	
	
	
	public int updateMember(Member m) {
		// update문 => 처리된 행수 => 트랜젝션 처리
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MEMBER SET USERPWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE USERID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 원래는 AutoCommit이 켜져있음!!
			conn.setAutoCommit(false); // 기본값은 true임! false로 바꿔주면 수동커밋! // 프로젝트 할 때 해주면 좋다★★
			
			pstmt = conn.prepareStatement(sql); // 미완성 ㅡ> 돌려봤자 안 돌아감
			pstmt.setString(1, m.getUserPwd()); // 1번 물음표
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());
				
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
		
	}
	
	
	
	public int deleteMember(String userId) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	

	
	
	
	
	
	
}//
