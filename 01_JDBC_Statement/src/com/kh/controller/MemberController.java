package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

// Controller : View를 통해서 사용자가 요청한 기능에 대해서 처리하는 담당
// 				해당 메소드로 전달된 데이터 [가공처리 한 후] Dao로 전달하면서 호출	// ㅡ 가공처리 : 가방에 주섬주섬 담는 거
//				Dao로 부터 반환 받은 결과에 따라 성공인지 실패인지 판단 후 응답화면 결정(View 메소드 호출)
public class MemberController {
	
	/**
	 * 사용자가 회원 추가 요청을 처리해주는 메소드
	 * @param userId ~ hobby : 사용자가 입력했던 정보들이 담겨있는 매개변수
	 * 
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String email, String phone, String address, String hobby) {
		
		// view로 부터 전달 받은 값을 바로 dao쪽으로 전달 x
		// 어딘가에(Member 객체) 주섬주섬 담아 전달 ㅡ vo에 담는거..
		
		// 방법1) 기본생성자로 생성한 후 각 필드의 setter 메소드를 통해서 일일히 담는 방법
		// 방법2) 아싸리 매개변수 생성자로 생성과 동시에 담는 방법 ㅡ sql 확인해서 시퀀스와 데이트인거는 제외해서 담기

//		Member m = new Member(); ㅡ 방법1			ㅡ 나이 string type이니까 형변환 시켜주기ㄱ
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby); // ㅡ 방법2 Member객체 m을 만든 거
		
//		System.out.println(m); // Member [userNo=0, userId=user02, userPwd=pass02, userName=김현지, gender=F, age=20, email=a@naver.com, phone=01077777777, address=인천, hobby=코딩하기, enrollDate=null]
		
		int result = new MemberDao().insertMember(m);
		
		if(result > 0) { // 성공
			new MemberMenu().displaySuccess("성공적으로 회원 추가 되었습니다.");
		}else { // 실패
			new MemberMenu().displayFail("회원 추가를 실패했습니다..");
		}
		
		
	}
	
	
	
	/**
	 * 사용자의 회원 전체 조회 요청을 처리해주는 메소드
	 */
	public void selectList() {
		
		ArrayList<Member> list = new MemberDao().selectList();
		
		// 조회 결과 있는지 없는지 판단 한 후 사용자가 보게 될 응답화면 결정
		if(list.isEmpty()) { // 텅 비어있을 경우 == 조회된 데이터 없었을 경우
			new MemberMenu().displayNoData("전체 조회 결과가 없습니다.");
		}else { // 뭐라도 조회된 데이터가 있을 경우
			new MemberMenu().displayMemberList(list);
		}
	
	}
	
	
	
	
	/**
	 * 사용자의 아이디로 회원 검색 요청을 처리해주는 메소드
	 * @param userId 사용자가 입력한 검색하고자 하는 회원 아이디값
	 */
	public void selectByUserId(String userId) {
		
		Member m = new MemberDao().selectByUserId(userId);
		
		if(m == null) { // 검색 결과가 없을 경우 (조회된 데이터 없음)
			new MemberMenu().displayNoData(userId + "에 해당하는 검색 결과가 없습니다.");
		}else { // 검색 결과가 있을 경우 (조회된 데이터 한 행 있음)
			new MemberMenu().displayMember(m);
		}

		
		
	}
	
	
	
	/**
	 * 이름으로 키워드 검색 요청 시 처리해주는 메소드
	 * @param keyword 사용자가 입력한 검색할 회원명(키워드)
	 */
	public void selectByUserName(String keyword) {
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		
		if(list.isEmpty()) { // 텅빈 리스트일 경우 => 검색 결과 없음
			new MemberMenu().displayNoData(keyword + "에 해당하는 검색 결과가 없습니다.");
		}else { // 그게 아닐 경우 => 검색 결과 있음
			new MemberMenu().displayMemberList(list); // 자바의 특징 : 메소드 재활용 가능
		}
		
	}
	
	
	
	/**
	 * 정보 변경 요청 처리해주는 메소드
	 * @param userId	: 변경하고자 하는 회원 아이디
	 * @param userPwd	: 변경할 비밀번호
	 * @param email		: 변경할 이메일
	 * @param phone		: 변경할 전화번호
	 * @param address	: 변경할 주소
	 */
	public void updateMember(String userId, String userPwd, String email, String phone, String address) {
		
		Member m = new Member(); // ㅡ 가방에 담는 거
		
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address); // ㅡ 이 작업을 안하면 밑에 매개변수 또 다 작성해줘야함
		
		int result = new MemberDao().updateMember(m);
		
		if(result > 0) { // 성공
			new MemberMenu().displaySuccess("성공적으로 회원 정보가 변경되었습니다.");
			
		}else { // 실패
			new MemberMenu().displayFail("회원 정보 변경에 실패했습니다.");
		}
				
	}
	
	
	public void deleteByUserId(String deleteUserId) {
		
		int result = new MemberDao().deleteByUserId(deleteUserId);
		
		if(result > 0) { // 성공
			new MemberMenu().displaySuccess("성공적으로 회원 탈퇴 되었습니다.");
			
		}else { // 실패
			new MemberMenu().displayFail("회원 탈퇴에 실패했습니다.");
		}
	}
	
	
	
	
	
	
	
	

}
