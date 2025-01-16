package com.kh.employee.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.employee.model.vo.Employee;

public class EmployeeDao {
	
	public int insertEmp(Employee e) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO COPY_EMP VALUES(SEQ_EMPNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, NULL, 'N')";
		
						
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kh", "kh");
			
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
			
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
								
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return result;
		

	}
	
	
	
	
	public ArrayList<Employee> selectList() {
		
		ArrayList<Employee> list = new ArrayList<Employee>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM COPY_EMP";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kh","kh");
			
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
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	
		
	}
	
	
	
	public int updateEmployee(Employee e) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE COPY_EMP SET EMAIL = ?, PHONE = ?, SALARY = ? WHERE EMP_ID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kh","kh");
			
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getEmail());
			pstmt.setString(2, e.getPhone());
			pstmt.setInt(3, e.getSalary());
			pstmt.setString(4, e.getEmpId());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return result;

	}
	
	
	
	
 //사원 삭제 
	public int deleteByEmployeeName(String deleteEmpName) {
	    int result = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    String sql = "DELETE FROM COPY_EMP WHERE EMP_NAME = ?";  // 이름으로 삭제

	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kh", "kh");
	        
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, deleteEmpName);

	        result = pstmt.executeUpdate();

	        if (result > 0) {
	            conn.commit();
	        } else {
	            conn.rollback();
	        }

	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            pstmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return result;
	}




	
	
	
	
	
	
	
	
	
	

}//
