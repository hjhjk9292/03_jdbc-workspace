package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Member;

// DAO(Data Access Object) : DB와 직접적으로 접근해서 사용자의 요청에 맞는 SQL문 실행 후 결과 받기(JDBC)
//							 결과를 Controller로 다시 리턴
public class MemberDao {
	
	/*
	 * * JDBC용 객체(객체 = 주소값을 갖고 있음)
	 * - Connection : DB의 연결 정보를 담고 있는 객체
	 * - [Prepared]Statement : 연결된 DB에 SQL문을 전달해서 실행하고 그 결과를 받아내는 객체 ******
	 * - resultSet : SELECT문 실행 후 조회된 결과물들이 담겨있는 객체
	 * 
	 * * JDBC 과정(순서중요!)
	 * 1) jdbc driver 등록 : 해당 DBMS(오라클)가 제공하는 클래스 등록
	 * 2) Connection 생성 : 연결하고자 하는 DB정보 입력해서 해당 DB와 연결하면서 생성
	 * 3) Statement 생성 : Connection 객체를 이용해서 생성(sql문 실행 및 결과 받는 객체) ㅡ 커넥션을 만들고 커넥션이 갖고 있는 메소드를 통해 스테이트먼트를 생성
	 * 4) sql문 전달하면서 실행 : Statement 객체를 이용해서 sql문 실행
	 * 5) 결과 받기
	 * 		> SELECT문 실행 => ResultSet 객체 (조회된 데이터들이 담겨있음) => 6_1)
	 * 		> 	 DML문 실행 => int (처리된 행수)						  => 6_2)
	 * 
	 * 6_1) ResultSet에 담겨있는 데이터들을 하나씩 하나씩 뽑아서 vo객체에 주섬주섬 옮겨 담기[+ ArrayList에 차곡차곡 담기] ㅡ 하나면 그냥 담고, 여러개면 만들어 담는거
	 * 6_2) 트랜젝션 처리 (성공적으로 수행했으면 commit, 실패했으면 rollback) ㅡ dml문에서 하는 거.. select절에선 안하지..
	 * 
	 * 7) 다 사용한 JDBC용 객체들 반드시!!! 자원 반납 (close) => 생성된 역순으로 ㅡ★ 반납 안하면 rock이 걸려서 삭제하거나 포맷해야할수도  
	 */
	
	/**
	 * 사용자가 입력한 정보들을 추가시켜주는 메소드
	 * @param m : 사용자가 입력한 값들이 주섬주섬 담겨있는 Member 객체
	 * @return : insert문 실행 후 처리된 행수
	 */
	public int insertMember(Member m) {		// public void insertMember(Member m) {  였는데 result 자료형 int라서 void -> int 로 변경
		// insert문 => 처리된 행수(int) => 트랜젝션 처리
		
		// 필요한 변수들 셋팅
		int result = 0;		// 처리된 결과(처리된 행수)를 받아줄 변수
		Connection conn = null;		// 연결된 DB의 연결정보를 담는 객체
		Statement stmt = null;		// "완성된 sql(실제값이 다 채워진 상태로)"문 전달해서 곧바로 실행 후 결과 받는 객체
		
		// 실행할 sql문 (완성된 형태로 만들어두기!!)
		// INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, 'XXXX', 'XXXX', 'XXX', 'X', XX, 'XXXXXXXXX', 'XXXXXXXXXXX', 'XX', 'XXXX', SYSDATE);
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL,"
						+ "'" + m.getUserId() + "', "
						+ "'" + m.getUserPwd() + "', "
						+ "'" + m.getUserName() + "', "
						+ "'" + m.getGender() + "', "
							  + m.getAge() + ", "
						+ "'" + m.getEmail() + "', "
						+ "'" + m.getPhone() + "', "
						+ "'" + m.getAddress() + "', "
						+ "'" + m.getHobby() + "', sysdate)"; 
						
//		System.out.println(sql);	// INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL,'user02', 'pass02', '김현지', 'F', 20, 'a@naver.com', '01044444444', '성남', '코딩하기', sysdate)
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver"); //surround 클릭
			
			// 2) Connection 객체 생성 == DB에 연결(url, 계정명, 비밀번호)		접속 url ㄱ
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC"); // 2번째 클릭
			
			// 3) Statement 객체 생성
			stmt =conn.createStatement();
			
			// 4, 5) sql문을 전달하면서 실행 후 결과(처리된 행수) 받기
			result = stmt.executeUpdate(sql);
			
			// 6) 트랜젝션 처리
			if(result > 0) { // 성공 => commit;
				conn.commit();
			}else { // 실패 => rollback
				conn.rollback();
			}
			
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				// 7) 다 쓴 Jdbc용 객체 반납
				stmt.close();	// surround with catch 2번째꺼
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result; // 1 아니면 0 // 결과를 result로 반환 -> result 자료형이 int라서 void 가 아닌 int로 변경 => public int insertMember(Member m) {
		
		
	}
	
	
	
	/**
	 * 사용자가 요청한 회원 전체 조회를 처리해주는 메소드
	 * @return 조회된 결과가 있었으면 결과들이 담겨있는 list | 조회된 결과가 없었으면 텅 빈 list
	 */
	public ArrayList<Member> selectList() { // public void -> public ArrayList<Member> selectList() {
		// select문 => ResultSet 객체 => ArrayList에 차곡차곡 담기
		
		// 필요한 변수들 셋팅
		ArrayList<Member> list = new ArrayList<Member>(); // [] 현재 상태는 텅 비어있는 상태
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null; // select문 실행 시 조회된 결과값들이 최초로 담기는 객체 ㅡ 표처럼 생긴 거 담는거임
		
		// 실행할 sql문
		String sql = "SELECT * FROM MEMBER";
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC"); // 2번째에 있는 add catch ~ surrounding~
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			// 4, 5) sql문 실행 및 결과 받기
			rset = stmt.executeQuery(sql);
			// 6) ResultSet 으로부터 데이터를 하나씩 뽑아서 vo 객체에 주섬주섬 담고 + list에 vo 객체 추가 ㅡ> 표 통채로 있는거를.. 한줄한줄 member 객체를 추가 list에
			while(rset.next()) {
				// 현재 rset의 커서가 가리키고 있는 한 행의 데이터들을 싹 뽑아서 Member 객체 주섬주섬 담기
				Member m = new Member();
				
				m.setUserNo(rset.getInt("USERNO")); // 대문자, 소문자 상관 없음 // 파란색 글씨는 DB 컬럼값 보고 입력
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("username"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				// 현재 참조하고 있는 행에 대한 모든 컬럼에 대한 데이터들을 하나의 Member 객체에 담기 끝!
				
				list.add(m); // 리스트에 해당 회원 객체 차곡차곡 담기
				
			}
			
			// 반복문이 다 끝난 시점에
			// 만약에 조회된 데이터가 없었다면 list가 텅 빈 상태일꺼임!
			// 만약에 조회된 데이터가 있었다면 list에 뭐라도 담겨있을것!
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 7) 다 쓴 jdbc용 객체 반납
				rset.close();
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list; //		텅 빈 리스트 | 뭐라도 담겨있는 리스트	// 리스트가 void인데 무엇을 반환하냐라는 오류가 뜰 것임 => public void -> public ArrayList<Member> selectList() {
		
		
		
	}

	
	
//*
	/**
	 * 사용자의 아이디로 회원 검색 요청 처리해주는 메소드
	 * @param userId	사용자가 입력한 검색하고자 하는 회원 아이디값
	 * @return			검색된 결과가 있었으면 생성된 Member 객체 | 검색된 결과가 없었으면 null
	 */
	public Member selectByUserId(String userId) {
		//select문(한행) => ResultSet => Member 객체 // 여러행은 ArrayList가 필요하겠지만
		
		Member m = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문 (완성된 형태로)
		// SELECT * FROM MEMBER WHERE USERID = 'XXX'
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = '" + userId + "'";
		
		try {
			// 1)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			// 3)
			stmt = conn.createStatement();
			// 4, 5) rset에 담으면서 쿼리를 돌리는 거
			rset = stmt.executeQuery(sql);
			
			//6) 해당 결과를 갖고 하나하나 뽑는건데 한 행이니까 while문 아님! if문으로
			if(rset.next()) { // 조회된 데이터가 있을때만
				// 조회된 데이터가 하나라도 있으면 ㅡ if문 적용
				// 해당 조회된 컬럼 값들을 쏙쏙 뽑아서 한 Member 객체의 각 필드에 담기
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
			
			// 위의 조건문 다 끝난 시점에
			// 만약에 조회된 데이터가 없었을 경우 => m은 null 상태
			// 만약에 조호된 데이터가 있었을 경우 => m은 생성된 객체 상태
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 7)
				rset.close();
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return m; // null | 생성된 Member 객체

			
		
	}
	
	
	public ArrayList<Member> selectByUserName(String keyword) { // void가 아니라 list의 자료형인 ArrayList<Member> 로 변경
		// select문(여러행) => ResultSet 객체 => ArrayList<Member> 객체
		
		ArrayList<Member> list = new ArrayList<Member>(); // [] : 텅빈 리스트
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// SELECT * FROM MEMBER WHERE USER NAME LIKE '%XX%'
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%" + keyword + "%'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				// 현재 rset 참조하고 있는 한 행 데이터들 뽑아서
				// 한 Member 객체에 주섬주섬..=> list에 추가
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
							   rset.getDate("enroll_date"))); // 이거는 list.add 바로 // 아까는 list.add 를 마지막에
						
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list; // 텅빈 리스트(원하는 데이터가 없는 거) | 뭐라도 담겨있는 리스트
		
	}
	
	
	
	public int updateMember(Member m) {	// void를 result의 자료형인 int로 변경
		// update문 => 처리된 행수(int) => 트랜젝션 처리
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		// 실행할 sql문 (완성형태)
		/*
		 * UPDATE MEMBER
		    SET USERPWD = 'XXXX'
		        , EMAIL = 'XXXX'
		        , PHONE = 'XXXXXXX'
		        , ADDRESS = 'XXXX'
			WHERE USERID = 'XXXX'
		 */
		String sql = "UPDATE MEMBER "
				   + 	"SET USERPWD = '" + m.getUserPwd() + "' "
				   +		", EMAIL = '" + m.getEmail() + "'"
				   +		", PHONE = '" + m.getPhone() + "'"
				   +	  ", ADDRESS = '" + m.getAddress() + "'"
				   +   "WHERE USERID = '" + m.getUserId() + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);	// ㅡ update문은 executeUpdate 메소드
			
			if(result > 0) {	// 트랜젝션 처리
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
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
	
	
	
	/**
	 * 사용자가 입력한 아이디값 전달 받아서 회원 탈퇴 시켜주는 메소드
	 * @param userId	사용자가 입력한 아이디값
	 * @return 처리된 행 수
	 */
	public int deleteMember(String userId) {
		/* DELETE FROM MEMBER WHERE USERID = '사용자가 입력한 아이디값' */
		// delete문 => 처리된 행수(int) => 트랜젝션 처리
		int result = 0;
		
		Connection conn = null; // ㅡ 객체 : 주소값 들어 있는 거, null로 초기화
		Statement stmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID = '" + userId + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC"); // conn에 담지 않으면 밑에서 NullPointexception 나올것임. null로 초기화 해뒀어서
			stmt = conn.createStatement(); 
			
			result = stmt.executeUpdate(sql); // 쿼리를 돌린 것을 result에 담아야함. 안하면 값이 위에서 설정한 0으로 나온다.
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) { // try문 것을 처리
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
				
			} catch (SQLException e) { // finally로 나왔으니까 따로 작성
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
	
	
	
/*	위에는 선생님의 코드 밑에는 내가 작성한 delete 코드
	public int deleteByUserId(String deleteUserId) {
		
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;

		
		String sql = "DELETE FROM MEMBER WHERE USERID = '" + deleteUserId + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();

			
			// 4, 5) sql문을 전달하면서 실행 후 결과(처리된 행수) 받기
			result = stmt.executeUpdate(sql);
			
			// 6) 트랜젝션 처리
			if(result > 0) { // 성공 => commit;
				conn.commit();
			}else { // 실패 => rollback
				conn.rollback();
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
		
	}
*/	


	
	
	
	
	
	
}//
