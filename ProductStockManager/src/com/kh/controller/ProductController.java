package com.kh.controller;

import java.sql.Date;
import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.model.vo.ProductIO;
import com.kh.view.ProductMenu;

public class ProductController {
	
	 private ProductService ps = new ProductService();

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

	// ----------------------------------------------------------------------

	// 전체 입출고 내역 조회
	public void selectProductIO() {

		ArrayList<ProductIO> list = new ProductService().selectProductIO();

		if (list.isEmpty()) {
			new ProductMenu().displayNoData("조회 결과가 없습니다.");
		} else {
			new ProductMenu().displayProductIOList(list);
		}

	}

	public ArrayList<ProductIO> selectProductInput() { 
	    return new ProductService().selectProductInput();
	}




	public void selectProductOutput() {
	    ArrayList<ProductIO> list = new ProductService().selectProductOutput();
	    if (list.isEmpty()) {
	        System.out.println("\n출고 내역이 없습니다.");
	    } else {
	        System.out.println("\n======== 출고 리스트 ========");
	        for (ProductIO io : list) {
	            System.out.println(io);
	        }
	    }
	}


    public int productInput(String productId, int amount) {
        return ps.productInput(new ProductIO(0, productId, null, null, amount, "입고"));
    }

    public int productOutput(String productId, int amount) {
    	return new ProductService().productOutput(productId, amount);
    }


}//
