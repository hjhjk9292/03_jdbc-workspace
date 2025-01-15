package com.kh.employee.controller;

import java.sql.Date;
import java.util.ArrayList;

import com.kh.employee.model.dao.EmployeeDao;
import com.kh.employee.model.vo.Employee;
import com.kh.employee.view.EmployeeMenu;


public class EmployeeController {
	
	
	
	/**
	 * 사용자가 사원 추가 요청을 처리해주는 메소드
	 * @param empId ~ managerId : 사용자가 입력했던 정보들이 담겨있는 매개변수
	 */
	public void insertEmp(String empId, String empName, String empNo, String email, String phone, String deptCode,
			String jobCode, String salLevel, String salary, String bonus, String managerId) {
		
		Employee e = new Employee(empId, empName, empNo, email, phone, deptCode,
				jobCode, salLevel, Integer.parseInt(salary), Double.parseDouble(bonus), managerId);
		
		System.out.print(e);
		
		int result = new EmployeeDao().insertEmp(e);
		
		if(result > 0) {
			new EmployeeMenu().displaySuccess("성공적으로 사원 추가 되었습니다.");			
		}else {
			new EmployeeMenu().displayFail("회원 추가를 실패했습니다.");
		}
		
				
	}
	
	
	
	public void selectList() {
		
		ArrayList<Employee> list = new EmployeeDao().selectList();
		
		if(list.isEmpty()) {
			new EmployeeMenu().displayNoData("전체 조회 결과가 없습니다.");
		}else {
			new EmployeeMenu().displayEmployeeList(list);
		}
		
	}
	
	
	
	public void updateEmployee(String empId, String email, String phone, String salary) {
		
		Employee e = new Employee();
		
		e.setEmpId(empId);
		e.setEmail(email);
		e.setPhone(phone);
		e.setSalary(Integer.parseInt(salary));
		
		int result = new EmployeeDao().updateEmployee(e); // DAO의 updateEmployee 호출
		
		System.out.println(e);
		
		if(result > 0){
	        new EmployeeMenu().displaySuccess("성공적으로 사원 정보가 변경되었습니다.");
	    } else {
	        new EmployeeMenu().displayFail("사원 정보 변경에 실패했습니다.");
	    }
	
		
		
	}
	
	
	
	public void deleteByEmployeeName(String deleteEmpName) {
        int result = new EmployeeDao().deleteByEmployeeName(deleteEmpName);

        // 삭제 성공 여부 출력
        if (result > 0) {
            new EmployeeMenu().displaySuccess("사원 삭제가 완료되었습니다.");
        } else {
        	new EmployeeMenu().displayFail("사원 삭제에 실패했습니다.");
        }
    }
	

}//
