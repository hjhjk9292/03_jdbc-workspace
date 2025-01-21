package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Product;
import com.kh.model.vo.ProductIO;

public class ProductDao {

	private Properties prop = new Properties();

	public ProductDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Product> selectList(Connection conn) {

		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectList");

		try {
			pstmt = conn.prepareStatement(sql); // 완성된 sql문
			rset = pstmt.executeQuery();

			while (rset.next()) {
				// 한 행 => Product 객체 => list 추가
				list.add(new Product(rset.getString("PRODUCT_ID")
								   , rset.getString("P_NAME")
								   , rset.getInt("PRICE")
								   , rset.getString("DESCRIPTION")
								   , rset.getInt("STOCK")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;

	}
	
	
	
	public int inputProduct(Connection conn, Product p) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("inputProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getProductId());
			pstmt.setString(2, p.getpName());
			pstmt.setInt(3, p.getPrice());
			pstmt.setString(4, p.getDescription());
			pstmt.setInt(5, p.getStock());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	public int updateProduct(Connection conn, Product p) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateProduct");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getpName());
			pstmt.setInt(2, p.getPrice());
			pstmt.setString(3, p.getDescription());
			pstmt.setInt(4, p.getStock());
			pstmt.setString(5, p.getProductId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	
	public int deleteProduct(Connection conn, String productId) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			
			result = pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	public ArrayList<Product> selectByProductName(Connection conn, String keyword){
		ArrayList<Product> list = new ArrayList<Product>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByProductName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				list.add(new Product(rset.getString("PRODUCT_ID")
						   , rset.getString("P_NAME")
						   , rset.getInt("PRICE")
						   , rset.getString("DESCRIPTION")
						   , rset.getInt("STOCK")));
	}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	// -------------------------------------------------------------------------

	public ArrayList<ProductIO> selectProductIO(Connection conn) {
		ArrayList<ProductIO> list = new ArrayList<ProductIO>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectProductIO");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				// 한 행 => Product 객체 => list 추가
				list.add(new ProductIO(rset.getInt("IO_NUM")
						, rset.getString("PRODUCT_ID")
						, rset.getString("P_NAME")																				// 존재x
						, rset.getDate("IO_DATE")
						, rset.getInt("AMOUNT")
						, rset.getString("STATUS")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;

	}

    // 입고 내역 조회
    public ArrayList<ProductIO> selectProductInput(Connection conn) {
        ArrayList<ProductIO> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectProductInput");
        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                list.add(new ProductIO(
                    rset.getInt("IO_NUM"),
                    rset.getString("PRODUCT_ID"),
                    rset.getString("P_NAME"),
                    rset.getDate("IO_DATE"),
                    rset.getInt("AMOUNT"),
                    rset.getString("STATUS")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return list;
    }

    // 출고 내역 조회
    public ArrayList<ProductIO> selectProductOutput(Connection conn) {
        ArrayList<ProductIO> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectProductOutput");
        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                list.add(new ProductIO(
                    rset.getInt("IO_NUM"),
                    rset.getString("PRODUCT_ID"),
                    rset.getString("P_NAME"),
                    rset.getDate("IO_DATE"),
                    rset.getInt("AMOUNT"),
                    rset.getString("STATUS")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return list;
    }


    // 입출고 내역 추가
    public int insertProductIO(Connection conn, ProductIO productIO) {
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = prop.getProperty("insertProductIO");
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productIO.getProductId());
            pstmt.setInt(2, productIO.getAmount());
            pstmt.setString(3, productIO.getStatus());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
    
    // 재고 확인
    public int checkStock(Connection conn, String productId) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        int stock = -1;
        String sql = prop.getProperty("checkStock");
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productId);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                stock = rset.getInt("STOCK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return stock;
    }


}
