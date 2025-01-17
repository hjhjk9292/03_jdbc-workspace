package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*; // 호출에서 작성 가능 코드가 간결해짐
import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

public class MemberService {
	
	// 1) jdbc driver 등록 
	// 2) Connection 객체 생성(접속을 위해)
	
	public int insertMember(Member m) {
		Connection conn = /*JDBCTemplate.*/getConnection();
		int result= new MemberDao().insertMember(conn, m);
		
		// 6) 트랜젝션 처리
		if(result > 0) {
			/*JDBCTemplate.*/commit(conn);
		}else {
			/*JDBCTemplate.*/rollback(conn);
		}
		
		/*JDBCTemplate.*/close(conn);
		return result;
	}
	
	
	
	
	public int deleteMember(String userId) {
		Connection conn = getConnection();
		int result = new MemberDao().deleteMember(conn, userId);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	
	public ArrayList<Member> selectList() {
		
		Connection conn = getConnection();
		ArrayList<Member> list = new MemberDao().selectList(conn);
		
		close(conn);
		
		return list;
	}
	
	
	
	public Member selectByUserId(String userId) {
		Connection conn = getConnection();
		Member m = new MemberDao().selectByUserId(conn, userId);
		
		close(conn);
		return m;
		
	}
	
	
	
	public ArrayList<Member> selectByUserName(String keyword){
		
		Connection conn = getConnection();
		ArrayList<Member> list = new MemberDao().selectByUserName(conn, keyword);
		
		close(conn);
		return list;
		
	}
	
	
	
	public int updateMember(Member m) {
		
		Connection conn = getConnection();
		int result = new MemberDao().updateMember(conn, m);
		
		if(result > 0) {
			commit(conn); // JDBCTemplate에 있는 commit 메소드
		}else {
			rollback(conn); // JDBCTemplate에 있는 rollback 메소드
		}
		
		close(conn);
		return result;
		
	}
	
	
	
	
	
	

}//
