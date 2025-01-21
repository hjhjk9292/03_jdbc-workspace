package com.kh.controller;

import java.sql.Date;
import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.model.vo.ProductIO;
import com.kh.view.ProductMenu;

public class ProductController {

	public void selectList() {

		ArrayList<Product> list = new ProductService().selectList();

		if (list.isEmpty()) {
			new ProductMenu().displayNoData("상품 전체 조회 결과가 없습니다.");
		} else {
			new ProductMenu().displayProductList(list);
		}

	}

	public void inputProduct(String productId, String pName, String price, String description, String stock) {

		Product p = new Product(productId, pName, Integer.parseInt(price), description, Integer.parseInt(stock));
		int result = new ProductService().inputProduct(p);

		if (result > 0) {
			new ProductMenu().displaySuccess("성공적으로 상품 추가 되었습니다.");
		} else {
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

		if (result > 0) {
			new ProductMenu().displaySuccess("상품 정보가 성공적으로 수정됐습니다.");
		} else {
			new ProductMenu().displayFail("상품 정보 수정에 실패했습니다.");
		}
	}

	public void deleteProduct(String productId) {

		int result = new ProductService().deleteProduct(productId);

		if (result > 0) {
			new ProductMenu().displaySuccess("상품 삭제되었습니다.");
		} else {
			new ProductMenu().displayFail(productId + "에 해당하는 상품이 없습니다.");
		}
	}

	public void selectByProductName(String keyword) {

		ArrayList<Product> list = new ProductService().selectByProductName(keyword);

		if (list.isEmpty()) {
			new ProductMenu().displayNoData(keyword + "에 해당하는 검색 결과가 없습니다.");
		} else {
			new ProductMenu().displayProductList(list);
		}

	}

	// ----------------------------------------------------------------------

	// 전체 입출고 내역 조회
	public void selectProductIO() {

		ArrayList<ProductIO> list = new ProductService().selectProductIO();

		if (list.isEmpty()) {
			new ProductMenu().displayNoData("상품 전체 조회 결과가 없습니다.");
		} else {
			new ProductMenu().displayProductIOList(list);
		}

	}

	// 입고 내역만 조회
	public void selectProductInput() {
		ArrayList<ProductIO> list = new ProductService().selectProductInput();
		if (list.isEmpty()) {
			new ProductMenu().displayNoData("입고 내역이 없습니다.");
		} else {
			new ProductMenu().displayProductIOList(list);
		}
	}

	// 출고 내역만 조회
	public void selectProductOutput() {
		ArrayList<ProductIO> list = new ProductService().selectProductOutput();
		if (list.isEmpty()) {
			new ProductMenu().displayNoData("출고 내역이 없습니다.");
		} else {
			new ProductMenu().displayProductIOList(list);
		}
	}

	// 상품 입고
	public void productInput(String productId, int amount) {
//		int result = new ProductService().productIntput();
//		if (result > 0) {
//			new ProductMenu().displaySuccess(productId);
//		}
	    ProductIO productIO = new ProductIO();
	    productIO.setProductId(productId);
	    productIO.setAmount(amount);
	    productIO.setStatus("입고");
	    
	    int result = new ProductService().productInput(productIO);
	    if (result > 0) {
	        new ProductMenu().displaySuccess("상품이 성공적으로 입고되었습니다.");
	    } else {
	        new ProductMenu().displayFail("상품 입고에 실패했습니다.");
	    }

	}

	// 상품 출고
	public boolean productOutput(String productId, int amount) {
	    ProductIO productIO = new ProductIO();
	    productIO.setProductId(productId);
	    productIO.setAmount(amount);
	    productIO.setStatus("출고");
	    
	    int result = new ProductService().productOutput(productId, amount);
	    if (result > 0) {
	        new ProductMenu().displaySuccess("상품이 성공적으로 출고되었습니다.");
	    } else {
	        new ProductMenu().displayFail("상품 출고에 실패했습니다.");
	    }
		return false;
	}



}//
