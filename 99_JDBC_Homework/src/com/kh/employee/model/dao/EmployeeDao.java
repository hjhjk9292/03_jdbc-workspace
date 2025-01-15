package com.kh.employee.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.employee.model.vo.Employee;

public class EmployeeDao {
	
	public int insertEmp(Employee e) {
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "INSERT INTO COPY_EMP VALUES(SEQ_EMPNO.NEXTVAL, "
						+ "'" + e.getEmpName() + "', "
						+ "'" + e.getEmpNo() + "', "
						+ "'" + e.getEmail() + "', "
						+ "'" + e.getPhone() + "', "
						+ "'" + e.getDeptCode() + "', "
						+ "'" + e.getJobCode() + "', "
						+ "'" + e.getSalLevel() + "', "
							  + e.getSalary() + ", "
						      + e.getBonus() + ", "
						+ "'" + e.getManagerId() + "', SYSDATE, NULL, 'N')";
		
		System.out.print(sql);
						
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kh", "kh");
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
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
				stmt.close();
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
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM COPY_EMP";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kh","kh");
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
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
				stmt.close();
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
		Statement stmt = null;
		
		String sql = "UPDATE COPY_EMP "
				   + "SET EMAIL = '" + e.getEmail() + "'"
				   +   ", PHONE = '" + e.getPhone() + "'"
				   +   ", SALARY = " + e.getSalary()  
				   +  " WHERE EMP_ID = '" + e.getEmpId() + "'";
		
		System.out.println(sql);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kh","kh");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
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
				stmt.close();
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
	    Statement stmt = null;

	    String sql = "DELETE FROM COPY_EMP WHERE EMP_NAME = '" + deleteEmpName + "'";  // 이름으로 삭제

	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kh", "kh");
	        stmt = conn.createStatement();

	        result = stmt.executeUpdate(sql);

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
	            stmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return result;
	}




	
	
	
	
	
	
	
	
	
	

}//
