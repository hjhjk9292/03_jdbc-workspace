package com.kh.employee.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.employee.controller.EmployeeController;
import com.kh.employee.model.vo.Employee;

public class EmployeeMenu {
	
	private Scanner sc = new Scanner(System.in);
	
	private EmployeeController ec = new EmployeeController();

/*
 * INSERT, UPDATE, DELETE, SELECT 테스트를 진행한다. (KH계정으로 진행!) 
 * (메뉴 구성 => 1. 사원추가 2. 사원전체조회 3. 사원수정 4. 사원삭제 0. 프로그램 종료) 
*/	
	public void mainMenu() {
		
		while(true) {
			
			System.out.println("\n== 사원 관리 프로그램 ==");
			System.out.println("1. 사원 추가");
			System.out.println("2. 사원 전체 조회");
			System.out.println("3. 사원 수정");
			System.out.println("4. 사원 삭제");
			System.out.println("0. 프로그램 종료");
			
			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1 : inputEmp(); break;
			case 2 : ec.selectList(); break;
			case 3 : updateEmployee(); break;
			case 4 : String deleteEmpName = deleteEmployee(); // 이름 입력받기
            ec.deleteByEmployeeName(deleteEmpName); // 이름으로 삭제 처리
            break;
			case 0 : System.out.println("이용해주셔서 감사합니다."); return;
			default : System.out.println("메뉴를 잘못 입력하셨습니다. 다시 입력해주세요.");
	
			}
			
		}
		
	}
	
	
	/**
	 * 사원 추가 창(서브화면)
	 * 추가하고자 하는 사원의 정보를 입력 받아서 사원 추가 요청하는 창 
	 */
	public void inputEmp() {
		System.out.println("\n==== 사원 추가 ====");
		
		String empId = ""; // 사용자가 입력하지 않음
		
		System.out.print("직원명 : ");
		String empName = sc.nextLine();
		
		System.out.print("주민등록번호 : ");
		String empNo = sc.nextLine();
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print("부서코드 : ");
		String deptCode = sc.nextLine();
		
		System.out.print("직급코드 : ");
		String jobCode = sc.nextLine();
		
		System.out.print("급여등급 : ");
		String salLevel = sc.nextLine();
		
// int = salary, double = bonus		
		System.out.print("급여 : ");
		String salary = sc.nextLine();
		
		System.out.print("보너스율 : ");
		String bonus = sc.nextLine();
		
//		sc.nextLine();
		
		System.out.print("관리자 사번 : ");
		String managerId = sc.nextLine();

		
		
		ec.insertEmp(empId, empName, empNo, email, phone, deptCode,
				jobCode, salLevel, salary, bonus, managerId);
	}


	
	//사용자에게 변경하려고 하는 사원의 사번을 입력 받은 뒤 
//	이메일, 전화번호, 급여를 변경하는 UPDATE를 진행한다.
	public void updateEmployee() {
		System.out.println("\n==== 사원 정보 변경 ====");
		
		System.out.print("사원번호 입력 : ");
		String empId = sc.nextLine();
		
		System.out.print("변경할 이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("변경할 전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print("변경할 급여 : ");
		String salary = sc.nextLine();
		
		ec.updateEmployee(empId, email, phone, salary);
		
	}
	
	
	
    public String deleteEmployee() {
        System.out.println("\n==== 사원 삭제 ====");
        System.out.print("삭제할 사원명 입력 : ");
        return sc.nextLine();

    }

	
	
	
		
	// -------------------------- 응답 화면 --------------------------
	
	public void displaySuccess(String message) {
		System.out.println("\n 서비스 요청 성공 : " + message);
	}
	
	
	public void displayFail(String message) {
		System.out.println("\n 서비스 요청 실패 : " + message);
	}
	
	
	
	
	
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
	
	
	public void displayEmployeeList(ArrayList<Employee> list) {
		System.out.println("\n 조회된 데이터는 다음과 같습니다. \n");
		
		for(Employee e : list) {
			System.out.println(e);
		}
		
	}
	
	
	
	
	
	
	
	
}//
