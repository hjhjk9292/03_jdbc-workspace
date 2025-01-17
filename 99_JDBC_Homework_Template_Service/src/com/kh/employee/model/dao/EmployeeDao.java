package com.kh.employee.model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.employee.model.vo.Employee;

public class EmployeeDao {
	
	public int insertEmp(Connection conn, Employee e) {
		
		int result = 0;
		PreparedStatement pstmt = null;		
		String sql = "INSERT INTO COPY_EMP VALUES(SEQ_EMPNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, NULL, 'N')";
		
						
		try {						
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getEmpName());
			pstmt.setString(2, e.getEmpNo());
			pstmt.setString(3, e.getEmail());
			pstmt.setString(4, e.getPhone());
			pstmt.setString(5, e.getDeptCode());
			pstmt.setString(6, e.getJobCode());
			pstmt.setString(7, e.getSalLevel());
			pstmt.setInt(8, e.getSalary());
			pstmt.setDouble(9, e.getBonus());
			pstmt.setString(10, e.getManagerId());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		

	}
	
	
	
	
	public ArrayList<Employee> selectList(Connection conn) {
		
		ArrayList<Employee> list = new ArrayList<Employee>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM COPY_EMP";
		
		try {
			
			pstmt = conn.prepareStatement(sql);	
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Employee e = new Employee();
				
				e.setEmpId(rset.getString("EMP_ID"));
				e.setEmpName(rset.getString("EMP_NAME"));
				e.setEmpNo(rset.getString("EMP_NO"));
				e.setEmail(rset.getString("EMAIL"));
				e.setPhone(rset.getString("PHONE"));
				e.setDeptCode(rset.getString("DEPT_CODE"));
				e.setJobCode(rset.getString("JOB_CODE"));
				e.setSalLevel(rset.getString("SAL_LEVEL"));
				e.setSalary(rset.getInt("SALARY"));
				e.setBonus(rset.getDouble("BONUS"));
				e.setManagerId(rset.getString("MANAGER_ID"));
				
				list.add(e);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);	

		}
		
		return list;
	
		
	}
	
	
	
	public int updateEmployee(Connection conn, Employee e) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE COPY_EMP SET EMAIL = ?, PHONE = ?, SALARY = ? WHERE EMP_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getEmail());
			pstmt.setString(2, e.getPhone());
			pstmt.setInt(3, e.getSalary());
			pstmt.setString(4, e.getEmpId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
			
		}
		
		return result;

	}
	
	
	
	
 //사원 삭제 
	public int deleteByEmployeeName(Connection conn, String deleteEmpName) {
	    int result = 0;
	    PreparedStatement pstmt = null;

	    String sql = "DELETE FROM COPY_EMP WHERE EMP_NAME = ?";  // 이름으로 삭제

	    try {	    	
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, deleteEmpName);

	        result = pstmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	         close(pstmt);
	         close(conn);
	       
	    }

	    return result;
	}




	
	
	
	
	
	
	
	
	
	

}//
