package com.kh.employee.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.employee.model.dao.EmployeeDao;
import com.kh.employee.model.vo.Employee;

public class EmployeeService {
	
	public int insertEmp(Employee e) {
		Connection conn = getConnection();
		int result = new EmployeeDao().insertEmp(conn, e);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
	
	public ArrayList<Employee> selectList() {
		
		Connection conn = getConnection();
		ArrayList<Employee> list = new EmployeeDao().selectList(conn);
		
		close(conn);
		
		return list;
	}
	
	
	public int updateEmployee(Employee e) {
		
		Connection conn = getConnection();
		int result = new EmployeeDao().updateEmployee(conn, e);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
		
	}
	
	
	public int deleteByEmployeeName(String deleteEmpName) {
		Connection conn = getConnection();
		int result = new EmployeeDao().deleteByEmployeeName(conn, deleteEmpName);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	
	

}
