package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*; // JDBCtemplate에 있는 모든 메소드를 static에 올림 // .* 과 static 키워드 작성
import com.kh.model.vo.Member;

// DAO(Data Access Object) : DB와 직접적으로 접근해서 사용자의 요청에 맞는 SQL문 실행 후 결과 받기(JDBC)
//							 결과를 Controller로 다시 리턴
public class MemberDao {

	/*
	 * 기존의 방식 : DAO 클래스에 사용자가 요청할때마다 실행해야되는 SQL문을 자바소스코드 내에 명시적으로 작성 => 정적 코딩 방식
	 * > 문제점 : SQL문을 수정해야될 경우 자바소스코드를 수정해야됨 => 수정된 내용을 반영시키고자 한다면 프로그램을 재기동 해야됨!
	 * 
	 * > 해결 방식 : SQL문들을 별도로 관리하는 외부파일로(.xml) 만들어서 실시간으로 그 파일에 기록된 SQL문을 읽어들여서 실행 => 동적코딩방식
	 */

	private Properties prop = new Properties();
	
	// 사용자가 어떤 서비스 요청할 때 마다 계속 new MemberDao().xxxx() 호출!!
	// 즉, 서비스 요청할 때 마다 이 기본생성자가 매번 실행됨!!
	
	public MemberDao() { // 기본 생성자
		
		// 여기에다가 뭔가를 쓰면 => 쿼리를 읽어들이는 코드를 쓰면!
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

	public int insertMember(Connection conn, Member m) {
		// insert문(dml) => 처리된 행수 => 트랜젝션 처리
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");

		try {
			// 3) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql); // 미완성된 sql문,,, ? 채워줘야함
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());

			// 4, 5) sql문 실행 및 결과 받기
			result = pstmt.executeUpdate(); // 쿼리가 돌게됨

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			// conn은 아직 절대로 반납하면 안됨!! 이따 service에서 트랜젝션 처리 해야됨!!
		}

		return result; // service로 결과가 가게 됨

	}

	public int deleteMember(Connection conn, String userId) {
		// delete문 => 처리된 행수 => 트랜젝션 처리

		int result = 0;

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}

	public ArrayList<Member> selectList(Connection conn) {
		// select문(여러행) => ReserltSet 객체 => ArrayList<Member>

		ArrayList<Member> list = new ArrayList<Member>(); // []
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectList");

		try {
			pstmt = conn.prepareStatement(sql); // 완성된 sql문
			rset = pstmt.executeQuery();

			while (rset.next()) {
				// 한 행 => Member 객체 => list 추가
				list.add(new Member(rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),
						rset.getString("username"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("email"), rset.getString("phone"), rset.getString("address"),
						rset.getString("hobby"), rset.getDate("enroll_date")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;

	}

	public Member selectByUserId(Connection conn, String userId) {
		// select문 (한행) => ResultSet 객체 => Member 객체
		Member m = null;

		PreparedStatement pstmt = null;

		ResultSet rset = null;

		String sql = prop.getProperty("selectByUserId");

		try {
			pstmt = conn.prepareStatement(sql); // 미완성
			pstmt.setString(1, userId); // ? 가 채워짐

			rset = pstmt.executeQuery(); // sql 표 데이터가 담김

			if (rset.next()) {
				m = new Member(rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),
						rset.getString("username"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("email"), rset.getString("phone"), rset.getString("address"),
						rset.getString("hobby"), rset.getDate("enroll_date"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return m;

	}

	public ArrayList<Member> selectByUserName(Connection conn, String keyword) {
		// select문(여러행) => ResultSet => ArrayList<Member>
		ArrayList<Member> list = new ArrayList<Member>(); // []

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectByUserName");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				// 한 행 => Member 객체 => list 추가
				list.add(new Member(rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),
						rset.getString("username"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("email"), rset.getString("phone"), rset.getString("address"),
						rset.getString("hobby"), rset.getDate("enroll_date")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int updateMember(Connection conn, Member m) {
		// update문(dml) => 처리된 행수 => 트랜젝션 처리(service가 받아줄것임)

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("updateMember");

		try {
			pstmt = conn.prepareStatement(sql); // 미완성
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	
	
	public String loginMember(Connection conn, String userId, String userPwd) {
		// select문() => ResultSet 객체 => String // Member 객체 안 만들어도 됨(표처럼 긴 데이터가 아닌) // 만약 age로만 구할거면 int
		String userName = null; // null로 초기화
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql); // 미완성
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery(); // 쿼리가 rset에 담김
			
			if(rset.next()) { // 로그인 성공
				userName = rset.getString("username"); // 실패하면 아이디나 비밀번호가 틀려서 데이터가 조회되지 않을테니 null값이겠지..
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt); // close 해줘야 락 안걸림
		}
		
		return userName;
	}
	
	
	

}//
