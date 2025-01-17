package com.kh.model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*; // JDBCtemplate에 있는 모든 메소드를 static에 올림 // .* 과 static 키워드 작성
import com.kh.model.vo.Member;

// DAO(Data Access Object) : DB와 직접적으로 접근해서 사용자의 요청에 맞는 SQL문 실행 후 결과 받기(JDBC)
//							 결과를 Controller로 다시 리턴
public class MemberDao {
	
		public int insertMember(Connection conn, Member m) {
			// insert문(dml) => 처리된 행수 => 트랜젝션 처리
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
			
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
			} finally{
				close(pstmt);
				// conn은 아직 절대로 반납하면 안됨!! 이따 service에서 트랜젝션 처리 해야됨!!
			}
			
			return result; //service로 결과가 가게 됨
			
		}
	

	
		
		public int deleteMember(Connection conn, String userId) {
			// delete문 => 처리된 행수 => 트랜젝션 처리
			
			int result = 0;
			
			PreparedStatement pstmt = null;
			String sql = "DELETE FROM MEMBER WHERE USERID = ?";
			
			
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
			
			String sql = "SELECT * FROM MEMBER ORDER BY USERNAME";
			
			try {
				pstmt = conn.prepareStatement(sql); // 완성된 sql문
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					// 한 행 => Member 객체 => list 추가
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
			
			String sql = "SELECT * FROM MEMBER WHERE USERID = ?";
			
			try {
				pstmt = conn.prepareStatement(sql); // 미완성
				pstmt.setString(1, userId); // ? 가 채워짐
				
				rset = pstmt.executeQuery(); // sql 표 데이터가 담김
				
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
									rset.getDate("enroll_date"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return m;
			
		}
		
		
		
		
		public ArrayList<Member> selectByUserName(Connection conn, String keyword){
			// select문(여러행) => ResultSet => ArrayList<Member>
			ArrayList<Member> list = new ArrayList<Member>(); //[]
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%' || ? || '%'";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, keyword);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					// 한 행 => Member 객체 => list 추가
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
			
			String sql = "UPDATE MEMBER SET USERPWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE USERID = ?";
			
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
		
		
		
		
		
		
		
	
	
}//
