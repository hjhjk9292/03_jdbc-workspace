package com.kh.view;

import java.util.Scanner;

import com.kh.controller.MemberController;

// View : 사용자가 보게 될 시각적인 요소(화면) 출력 및 입력
public class MemberMenu {
	
	// Scanner 객체 생성 (전역으로 다 쓸수 있도록) ㅡ global 변수
	private Scanner sc = new Scanner(System.in);
	
	// MemberController 객체 생성 (전역으로 다 쓸 수 있도록)
	private MemberController mc = new MemberController();
	
	/** alt + shift + j 장점 : 마우스 대면 설명 볼 수 있음
	 * 사용자가 보게 될 첫 화면 (메인화면)
	 */
	public void mainMenu() {
		
		while(true) {
			
			System.out.println("\n== 회원 관리 프로그램 ==");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디 검색");
			System.out.println("4. 회원 이름으로 키워드 검색");
			System.out.println("5. 회원 정보 수정");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			
			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1 : inputMember(); break;
			case 2 : /* */ break;
			case 3 : /* */ break;
			case 4 : /* */ break;
			case 5 : /* */ break;
			case 6 : /* */ break;
			case 0 : System.out.println("이용해주셔서 감사합니다."); return;
			default : System.out.println("메뉴를 잘못 입력하셨습니다. 다시 입력해주세요");
			
			
			}
			
	
		}
		

		
	}
	
	
	/**
	 * 회원 추가 창(서브화면)
	 * 즉, 추가하고자 하는 회원의 정보를 입력 받아서 회원 추가 요청하는 창
	 * 
	 */
	public void inputMember() {
		
		System.out.println("\n==== 회원 추가 ====");
		// 아이디 ~ 취미 까지 입력 받기	ㅡ 변수 이름은 vo에 있는 이름과 같게 해주면 좋음
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		
		System.out.print("비밀번호  :");
		String userPwd = sc.nextLine();
		
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		
		System.out.print("성별(M/F) : ");
		String gender = sc.nextLine().toUpperCase();
		
		System.out.print("나이 : ");
//		int age = sc.nextInt(); 웹에서 숫자를 입력해도 문자로 넘어오기 때문에 ㄱ
		String age = sc.nextLine(); // "19"
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("전화번호(-빼고 입력) : ");
		String phone = sc.nextLine();
		
		System.out.print("주소 : ");
		String address = sc.nextLine();
		
		System.out.print("취미(,로 연이어서 작성) : ");
		String hobby = sc.nextLine();
		
		// 회원 추가 요청 == controller 메소드 호출						ㅡ 사용자에게 받았던 모든 값을 가져가야함
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
	}

}
