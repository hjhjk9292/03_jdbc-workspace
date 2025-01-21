package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;
import com.kh.model.vo.ProductIO;
import com.kh.view.ProductMenu;

public class ProductService {
	
	private ProductDao dao = new ProductDao();

	public ArrayList<Product> selectList(){
		Connection conn = getConnection();
		ArrayList<Product> list = new ProductDao().selectList(conn);
		
		close(conn);
		
		return list;
	}

	
	public int inputProduct(Product p) {
		Connection conn = getConnection();
		int result = new ProductDao().inputProduct(conn, p);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	
	
	public int updateProduct(Product p) {
		Connection conn = getConnection();
		int result = new ProductDao().updateProduct(conn, p);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	
	
	public int deleteProduct(String productId) {
		Connection conn = getConnection();
		int result = new ProductDao().deleteProduct(conn, productId);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	
	public ArrayList<Product> selectByProductName(String keyword){
		
		Connection conn = getConnection();
		ArrayList<Product> list = new ProductDao().selectByProductName(conn, keyword);
		
		close(conn);
		return list;
	}

	// ----------------------------------------------------------------------

	public ArrayList<ProductIO> selectProductIO() {
		Connection conn = getConnection();
		ArrayList<ProductIO> list = new ProductDao().selectProductIO(conn);

		close(conn);

		return list;

	}

    public ArrayList<ProductIO> selectProductInput() {
        Connection conn = getConnection();
        ArrayList<ProductIO> list = new ProductDao().selectProductIO(conn);
        close(conn);
        return list;
    }

    public ArrayList<ProductIO> selectProductOutput() {
        Connection conn = getConnection();
        ArrayList<ProductIO> list = new ProductDao().selectProductIO(conn);
        close(conn);
        return list;
    }


    // 상품 입고
    public int productInput(ProductIO productIO) {
        Connection conn = getConnection();
        int result = dao.insertProductIO(conn, productIO); // 트리거가 재고 업데이트 처리
        if (result > 0) commit(conn);
        else rollback(conn);
        close(conn);
        return result;
    }

    // 상품 출고
    public int productOutput(String productId, int amount) {
        Connection conn = getConnection();
        int currentStock = dao.checkStock(conn, productId); // 현재 재고 확인
        if (currentStock < amount) {
            close(conn);
            return -1; // 재고 부족
        }
        int result1 = dao.insertProductIO(conn, new ProductIO(0, productId, null, null, amount, "출고")); // 입출고 내역 추가
        if (result1 > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);
        return result1 > 0 ? 1 : 0; // 성공 시 1, 실패 시 0
    }


}//
