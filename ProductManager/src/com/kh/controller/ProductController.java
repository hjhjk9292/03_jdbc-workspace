package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.view.ProductMenu;

public class ProductController {
	
	public void selectList() {
		
		ArrayList<Product> list = new ProductService().selectList();
		
		if(list.isEmpty()) {
			new ProductMenu().displayNoData("상품 전체 조회 결과가 없습니다.");
		}else {
			new ProductMenu().displayProductList(list);
		}
		
		
	}
	
	
	
	public void inputProduct(String productId, String pName, String price, String description, String stock) {
		
		Product p = new Product(productId, pName, Integer.parseInt(price), description, Integer.parseInt(stock));
		int result = new ProductService().inputProduct(p);
		
		if(result > 0) {
			new ProductMenu().displaySuccess("성공적으로 상품 추가 되었습니다.");
		}else {
			new ProductMenu().displayFail("상품 추가 실패 ㅠㅠ");
		}
	}
	
	
	
	public void updateProduct(String productId, String pName, String price, String description, String stock) {
		Product p = new Product();
		p.setProductId(productId);
		p.setpName(pName);
		p.setPrice(Integer.parseInt(price));
		p.setDescription(description);
		p.setStock(Integer.parseInt(stock));
		
		int result = new ProductService().updateProduct(p);
		
		if(result > 0) {
			new ProductMenu().displaySuccess("상품 정보가 성공적으로 수정됐습니다.");
		}else {
			new ProductMenu().displayFail("상품 정보 수정에 실패했습니다.");
		}
	}
	
	
	
	public void deleteProduct(String productId) {
		
		int result = new ProductService().deleteProduct(productId);
		
		if(result > 0){
			new ProductMenu().displaySuccess("상품 삭제되었습니다.");
		}else {
			new ProductMenu().displayFail(productId + "에 해당하는 상품이 없습니다.");
		}
	}
	
	
	
	public void selectByProductName(String keyword) {
		
		ArrayList<Product> list = new ProductService().selectByProductName(keyword);
		
		if(list.isEmpty()) {
			new ProductMenu().displayNoData(keyword + "에 해당하는 검색 결과가 없습니다.");
		}else {
			new ProductMenu().displayProductList(list);
		}
		
	}
	
	

}//
