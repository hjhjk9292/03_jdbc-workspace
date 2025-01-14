package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

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
			case 2 : mc.selectList(); break;
			case 3 : String userId = inputMemberId(); 
					 mc.selectByUserId(userId);
					 break;
			case 4 : String keyword = inputMemberName();
					 mc.selectByUserName(keyword);
					break;
			case 5 : updateMember(); break;
			case 6 : String deleteUserId = deleteMember(); 
					 mc.deleteByUserId(deleteUserId); break;
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
		
		System.out.print("비밀번호 : ");
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
	
	
	
	
	
	/**
	 * 사용자에게 회원 아이디 입력 받은 후 그 때 입력된 값을 반환시켜주는 메소드
	 * @return 사용자가 입력한 아이디값
	 */
	public String inputMemberId() {
		System.out.print("\n회원 아이디 입력 : ");
		return sc.nextLine();
	}
	
	
	
	/**
	 * 사용자에게 검색할 회원명(키워드) 입력 받은 후 그 때 입력된 값을 반환시켜주는 메소드
	 * @return 사용자가 입력한 회원명(키워드)
	 */
	public String inputMemberName() {
		System.out.print("\n 회원 이름(키워드) 입력 : ");
		return sc.nextLine();
	}
	
	
	
	/**
	 * 사용자에게 변경할 정보들(비번, 이메일, 전화번호, 주소)과 해당 회원의 아이디 입력 받는 화면
	 */
	public void updateMember() {
		
		System.out.println("\n==== 회원 정보 변경 ====");
		
		// 회원 아이디, 변경할 비밀번호, 변경할 이메일, 변경할 전화번호, 변경할 주소
		
//		System.out.print("회원 아이디 입력 : "); 변수는 vo 이름과 동일하게
		String userId = inputMemberId(); // 메소드 재활용
		
		System.out.print("변경할 비밀번호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("변경할 이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("변경할 전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print("변경할 주소 : ");
		String address = sc.nextLine();
		
		mc.updateMember(userId, userPwd, email, phone, address);
			
	}
	
	
	
	/**
	 * 사용자 정보 삭제를 위해 해당 회원 아이디 입력 받는 화면
	 * @return
	 */
	public String deleteMember() {
		System.out.print("\n 탈퇴할 회원 아이디 입력 : ");
		return sc.nextLine();
	}
	
	
	
	
	
	
	// ------------------------------------------- 응답화면 ----------------------------------------------------
	
	/**
	 * 서비스 요청 처리 후 성공했을 경우 사용자가 보게 될 응답화면
	 * @param message 성공 메시지
	 */
	public void displaySuccess(String message) {
		System.out.println("\n 서비스 요청 성공 : " + message);
	}
	
	/**
	 * 서비스 요청 처리 후 실패했을 경우 사용자가 보게 될 응답화면
	 * @param message 실패 메시지
	 */
	public void displayFail(String message) {
		System.out.println("\n 서비스 요청 실패 : " + message);
	}

	
	/**
	 * 조회 서비스 요청 시 조회결과가 없을 경우 사용자가 보게 될 응답화면
	 * @param message
	 */
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
	
	
	
	
	/**
	 * 조회 서비스 요청 시 조회 결과가 여러 행일 경우 사용자가 보게 될 응답화면
	 * @param list
	 */
	public void displayMemberList(ArrayList<Member> list) {
		System.out.println("\n 조회된 데이터는 다음과 같습니다. \n");
		
		/* for loop문
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		*/
		
		// for each문(향상된 for문)
		for(Member m : list) { // m = list.get(0) => list.get(1) ....
			System.out.println(m);
		}
	
	
	}
	
	
	/**
	 * 조회 서비스 요청 시 조회 결과가 한 행일 경우 사용자가 보게 될 응답화면
	 * @param m
	 */
	public void displayMember(Member m) {
		System.out.println("\n 조회된 데이터는 다음과 같습니다.\n");
		System.out.println(m);
	}
	
	
	
	
}
	
	

