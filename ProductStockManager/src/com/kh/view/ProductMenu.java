package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;
import com.kh.model.vo.ProductIO;

public class ProductMenu {

	private Scanner sc = new Scanner(System.in);
	private ProductController pc = new ProductController();

	public void mainMenu() {
		while (true) {
			System.out.println("\n===== 상품 관리 프로그램 =====");
			System.out.println("1. 상품 전체 조회");
			System.out.println("2. 상품 추가");
			System.out.println("3. 상품 수정");
			System.out.println("4. 상품 삭제");
			System.out.println("5. 상품 검색");
			System.out.println("6. 상품 입출고 메뉴");
			System.out.println("0. 프로그램 종료하기");

			System.out.print("번호 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				pc.selectList();
				break;
			case 2:
				inputProduct();
				break;
			case 3:
				updateProduct();
				break;
			case 4:
				pc.deleteProduct(inputProductId());
				break;
			case 5:
				pc.selectByProductName(inputProductName());
				break;
			case 6:
				productIOMenu();
				break;
			case 0:
				System.out.println("이용해주셔서 감사합니다.");
				return;
			default:
				System.out.println("메뉴를 잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}



	public void inputProduct() {
		System.out.println("\n==== 상품 추가 ====");
		System.out.print("상품 아이디 : ");
		String productId = sc.nextLine();
		
		System.out.print("상품명 : ");
		String pName = sc.nextLine();
		
		System.out.print("상품 가격 : ");
		String price = sc.nextLine();
		
		System.out.print("상품 상세 정보 : ");
		String description = sc.nextLine();
		
		System.out.print("재고 : ");
		String stock = sc.nextLine();

		pc.inputProduct(productId, pName, price, description, stock);
	}

	public void updateProduct() {
		System.out.println("\n==== 상품 정보 변경 ====");
		String productId = inputProductId();
		
		System.out.print("변경할 상품명 : ");
		String pName = sc.nextLine();
		
		System.out.print("변경할 상품 가격 : ");
		String price = sc.nextLine();
		
		System.out.print("변경할 상품 상세 정보 : ");
		String description = sc.nextLine();
		
		System.out.print("변경할 상품 재고 : ");
		String stock = sc.nextLine();

		pc.updateProduct(productId, pName, price, description, stock);
	}


	public String inputProductId() {
		System.out.print("\n상품 아이디 입력 : ");
		return sc.nextLine();
	}

	public String deleteProduct() {
		System.out.println("\n 삭제할 상품 아이디 입력 : ");
		return sc.nextLine();
	}

	public String inputProductName() {
		System.out.print("\n 상품 이름(키워드) 입력 : ");
		return sc.nextLine();
	}

	// --------------------------- 입출고 메뉴 -----------------------------------
	
	public void productIOMenu() {
		while (true) {
			System.out.println("\n====== 입출고 메뉴 ======");
			System.out.println("1. 전체 입출고 내역 조회");
			System.out.println("2. 입고 내역 조회");
			System.out.println("3. 출고 내역 조회");
			System.out.println("4. 상품 입고");
			System.out.println("5. 상품 출고");
			System.out.println("9. 메인 메뉴로 돌아가기");

			System.out.print("번호 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				pc.selectProductIO();
				break;
			case 2:
				displayProductInputList(pc.selectProductInput());
				break;
			case 3:
				pc.selectProductOutput();
				break;
			case 4:
				productInput();
				break;
			case 5:
				productOutput();
				break;
			case 9:
				return;
			default:
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void productInput() {
		System.out.print("상품 아이디 : ");
		String productId = sc.nextLine();
		System.out.print("입고 수량 : ");
		int amount = sc.nextInt();
		sc.nextLine();

		int result = pc.productInput(productId, amount);
		if (result > 0) {
			System.out.println("서비스 요청 성공 : 성공적으로 입고하였습니다.");
		} else {
			System.out.println("서비스 요청 실패 : 입고에 실패했습니다.");
		}
	}

	public void productOutput() {
		System.out.print("상품 아이디 : ");
		String productId = sc.nextLine();
		System.out.print("출고 수량 : ");
		int amount = sc.nextInt();
		sc.nextLine();

		int result = pc.productOutput(productId, amount);
		if (result > 0) {
			System.out.println("서비스 요청 성공 : 성공적으로 출고하였습니다.");
		} else if (result == -1) {
			System.err.println("서비스 요청 실패 : 출고하고자 하는 제품의 재고수량이 부족합니다.");
		} else {
			System.out.println("서비스 요청 실패 : 출고에 실패했습니다.");
		}
	}

	// ---------------------------------- 응답 화면 --------------------------------

	public void displaySuccess(String message) {
		System.out.println("\n 서비스 요청 성공 : " + message);
	}

	public void displayFail(String message) {
		System.out.println("\n 서비스 요청 실패 : " + message);
	}

	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}

	public void displayProductList(ArrayList<Product> list) {
		System.out.println("\n======== 상품 리스트 ========");

		for (Product p : list) {
			System.out.println(p);
		}
	}

	public void displayProduct(Product p) {
		System.out.println("\n 조회된 데이터는 다음과 같습니다.\n");
		System.out.println(p);
	}
	
	
	//-----------------------------------------------------

	public void displayProductIOList(ArrayList<ProductIO> list) {
		System.out.println("\n===== 입출고 리스트 =====");
		for (ProductIO io : list) {
			System.out.println(io);
		}
	}
	
	public void displayProductInputList(ArrayList<ProductIO> list) { 
	    if (list.isEmpty()) {
	        System.out.println("\n입고 내역이 없습니다.");
	    } else {
	        System.out.println("\n======= 입고 리스트 =======");
	        for (ProductIO io : list) {
	            System.out.println(io);
	        }
	    }
	}


}//
