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
			System.out.println("1. 전체 조회 하기");
			System.out.println("2. 상품 추가 하기");
			System.out.println("3. 상품 수정 하기"); // 상품 id로 조회하고 수정
			System.out.println("4. 상품 삭제 하기"); // 상품 id로 조회해서 삭제
			System.out.println("5. 상품 검색 하기"); // 상품 이름으로 키워드 검색
			System.out.println("6. 상품 입출고 메뉴");
			System.out.println("0. 프로그램 종료하기");

			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine(); // 버퍼 제거

			switch (menu) {
			case 1:
				pc.selectList(); // 전체 상품 조회
				break;
			case 2:
				inputProduct(); // 상품 추가
				break;
			case 3:
				updateProduct(); // 상품 수정(상품id로 조회)
				break;
			case 4:
				pc.deleteProduct(inputProductId());// 상품 삭제(상품id로 조회)
				break;
			case 5:
				pc.selectByProductName(inputProductName()); // 상품 검색(상품 이름으로 키워드 검색)
				break;
			case 6:
				productIOMenu(); // 상품 입출고 메뉴
				break;
			case 0:
				System.out.println("이용해주셔서 감사합니다.");
				return;
			default:
				System.out.println("메뉴를 잘못 입력하셨습니다. 다시 입력해주세요");

			}

		}

	}

	public void productIOMenu() {

		while (true) {
			System.out.println("\n====== 입출고 메뉴 ======");
			System.out.println("1. 전체 입출고 내역 조회");
			System.out.println("2. 입고 내역만 조회");
			System.out.println("3. 출고 내역만 조회");
			System.out.println("4. 상품 입고");// 입고할 상품ID와 입고수량 입력받기
			System.out.println("5. 상품 출고");// 출고할 상품ID와 출고수량 입력받기
			System.out.println("9. 메인 메뉴로 돌아가기");

			System.out.print("번호 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				pc.selectProductIO(); // 전체 입출고 내역 조회
				break;
			case 2:
				pc.selectProductInput(); // 입고 내역만 조회
				break;
			case 3:
				pc.selectProductOutput(); // 출고 내역만 조회
				break;
			case 4:
				ProductInput(); // 상품 입고
				break;
			case 5:
				ProductOutput(); // 상품 출고
				break;
			case 9:
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}

		}
	}

	public void inputProduct() {

		System.out.println("\n==== 상품 추가 ====");
		// 상품 아이디 ~ 재고 까지 입력 받기 ㅡ 변수 이름은 vo에 있는 이름과 같게 해주면 좋음
		System.out.print("상품 아이디 : ");
		String productId = sc.nextLine();

		System.out.print("상품명 : ");
		String pName = sc.nextLine();

		System.out.print("상품가격 : ");
//		int price = sc.nextInt();
		String price = sc.nextLine();

		System.out.print("상품 상세 정보 : ");
		String description = sc.nextLine();

		System.out.print("재고 : ");
//		int stock = sc.nextInt(); 웹에서 숫자를 입력해도 문자로 넘어오기 때문에 ㄱ
		String stock = sc.nextLine(); // "10"

		// 상품 추가 요청 == controller 메소드 호출 ㅡ 사용자에게 받았던 모든 값을 가져가야함
		pc.inputProduct(productId, pName, price, description, stock);

	}

	public void updateProduct() {
		System.out.println("\n==== 상품 정보 변경 ====");

//		System.out.print("회원 아이디 입력 : ");
//		String productId = sc.nextLine();
		String productId = inputProductId();

		System.out.println("변경할 상품명 : ");
		String pName = sc.nextLine();

		System.out.print("변경할 상품가격 : ");
		String price = sc.nextLine();

		System.out.print("변경할 상품 상세 정보 : ");
		String description = sc.nextLine();

		System.out.print("변경할 상품 재고 : ");
		String stock = sc.nextLine();

		pc.updateProduct(productId, pName, price, description, stock);

	}

	public String inputProductId() {
		System.out.print("\n회원 아이디 입력 : ");
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

	public void ProductInput() {
		System.out.print("상품 아이디 : ");
		String productId = sc.nextLine();
		
		System.out.print("입고 수량 : ");
		int amount = sc.nextInt();
		sc.nextLine();
		
//		pc.productInput(productId, amount);
//		System.out.println("상품 입고 처리가 완료되었습니다.");

	}

	public void ProductOutput() {
		System.out.print("상품 아이디 : ");
		String productId = sc.nextLine();
		
		System.out.print("출고 수량 : ");
		int amount = sc.nextInt();
		sc.nextLine();
		
		boolean result = pc.productOutput(productId, amount);
		if(result) {
			System.out.println("성공적으로 출고하였습니다.");
		} else {
			System.err.println("서비스 요청 실패 : 출고하고자 하는 제품의 재고 수량이 부족합니다.");
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
		System.out.println("\n 조회된 데이터는 다음과 같습니다. \n");

		for (Product p : list) {
			System.out.println(p);
		}
	}

	public void displayProduct(Product p) {
		System.out.println("\n 조회된 데이터는 다음과 같습니다.\n");
		System.out.println(p);
	}

	public void displayProductIOList(ArrayList<ProductIO> list) {
		System.out.println("\n===== 상품 리스트 =====");
		for (ProductIO io : list) {
			System.out.println(io);
		}
	}

}//
