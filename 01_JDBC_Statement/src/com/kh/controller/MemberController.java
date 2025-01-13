package com.kh.controller;

import com.kh.model.vo.Member;

// Controller : View를 통해서 사용자가 요청한 기능에 대해서 처리하는 담당
// 				해당 메소드로 전달된 데이터 [가공처리 한 후] Dao로 전달하면서 호출
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

//		Member m = new Member(); ㅡ 방법1						ㅡ 나이는 형변환 시켜주기ㄱ
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby); // ㅡ 방법2 Member객체 m을 만든 거
	}

}
