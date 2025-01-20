package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;
import com.kh.controller.BookController;
import com.kh.model.vo.Book;
import com.kh.model.vo.Magazine;

public class BookMenu {
	private BookController bc = new BookController();
	private Scanner sc = new Scanner(System.in);

	public void mainMenu() {
		System.out.println("\nKH 우리반전용 도서관 관리 페이지입니다.");

		while (true) {
			System.out.println("===============================");
			System.out.println("1. 조회하기");
			System.out.println("2. 추가하기");
			System.out.println("3. 책 찾기");
			System.out.println("4. 전체책 가격 합계 및 평균 조회");
			System.out.println("5. 프로그램 종료");
			System.out.println("===============================");

			System.out.print("메뉴번호를 입력해주세요 : ");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				viewBooks();
				break;
			case 2:
				addBook();
				break;
			case 3:
				searchBook();
				break;
			case 4:
				showTotalAndAverage();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	private void getTotalAndAvgPrice() {
		// TODO Auto-generated method stub

	}

	private void viewBooks() {
		System.out.println("\n어떤 방법으로 책을 조회하겠습니까?");
		System.out.println("1. 도서관 전체 소장책 조회하기");
		System.out.println("2. 일반도서만 조회하기(잡지 제외)");
		System.out.println("3. 잡지만 조회하기(일반도서 제외)");
		System.out.println("4. 이전으로");

		System.out.print("메뉴번호를 입력해주세요 : ");
		int choice = sc.nextInt();
		sc.nextLine();

		switch (choice) {
		case 1:
			bc.getAllBooks(); // 전체 도서 출력
			break;
		case 2:
			bc.onlySearchBooks(); // 일반 도서 출력
			break;
		case 3:
			bc.onlySearchMagazines(); // 잡지 출력
			break;
		case 4:
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
		}
	}

	private void addBook() {
		System.out.print("책 제목을 입력하세요 : ");
		String title = sc.nextLine();

		System.out.print("작가를 입력하세요 : ");
		String author = sc.nextLine();

		System.out.print("출판사를 입력하세요 : ");
		String publisher = sc.nextLine();

		System.out.print("가격을 입력하세요 : ");
		int price = sc.nextInt();
		sc.nextLine();

		System.out.print("간단한 설명을 입력하세요 : ");
		String description = sc.nextLine();

		System.out.print("일반도서이면 true, 잡지이면 false를 입력하세요 : ");
		boolean isBook = sc.nextBoolean();
		sc.nextLine();

		if (isBook) {
			Book book = new Book(null, title, author, publisher, price, description);
			bc.addBook(book);
		} else {
			System.out.print("출간연도를 입력하세요 : ");
			int year = sc.nextInt();
			System.out.print("출간월을 입력하세요 : ");
			int month = sc.nextInt();
			sc.nextLine();

			Magazine magazine = new Magazine(null, title, author, publisher, price, description, year, month);
			bc.addMagazine(magazine);
		}
	}

	private void searchBook() {
		System.out.println("\n어떤 방법으로 책을 찾겠습니까?");
		System.out.println("1. bNo으로 책 찾기");
		System.out.println("2. 책 제목으로 책 찾기");
		System.out.println("3. 출간연도로 잡지 찾기");
		System.out.println("4. 출판사로 책 찾기");
		System.out.println("5. 특정 가격 밑으로 책 찾기");
		System.out.println("6. 이전으로");

		System.out.print("메뉴번호를 입력해주세요 : ");
		int choice = sc.nextInt();
		sc.nextLine();

		switch (choice) {
		case 1:
			System.out.print("bNo을 입력하세요 : ");
			String bNo = sc.nextLine();
			bc.searchBookBybNo(bNo);
			break;
		case 2:
			System.out.print("책 제목을 입력하세요 : ");
			String title = sc.nextLine();
			bc.searchBookByTitle(title);
			break;
		case 3:
			System.out.print("출간연도를 입력하세요 : ");
			int year = sc.nextInt();
			sc.nextLine();
			bc.magazineOfThisYearInfo(year);
			break;
		case 4:
			System.out.print("출판사를 입력하세요 : ");
			String publisher = sc.nextLine();
			bc.searchBookByPublisher(publisher);
			break;
		case 5:
			System.out.print("가격을 입력하세요 : ");
			int price = sc.nextInt();
			sc.nextLine();
			bc.searchBookByPrice(price);
			break;
		case 6:
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
		}
	}

	public void displayBookList(ArrayList<Book> bookList) {
		System.out.println("========= 도서 목록 =========");
		for (Book book : bookList) {
			System.out.println(book);
		}
	}

	public void displayMagazineList(ArrayList<Magazine> magazineList) {
		System.out.println("========= 잡지 목록 =========");
		for (Magazine magazine : magazineList) {
			System.out.println(magazine);
		}
	}

	private void showTotalAndAverage() {
		bc.getTotalAndAvgPrice(); // BookController의 getTotalAndAvgPrice 호출
	}

	public void displayTotalAndAvgPrice(int total, double avg) {
		System.out.println("========= 도서 가격 정보 =========");
		System.out.println("전체 책 가격 합계: " + total + "원");
		System.out.println("전체 책 가격 평균: " + String.format("%.2f", avg) + "원");
	}

	// --------------------------------------------------

	public void displaySuccess(String message) {
		System.out.println("[성공] " + message);
	}

	public void displayFail(String message) {
		System.out.println("[실패] " + message);
	}

	public void displayNoData(String message) {
		System.out.println("[조회 결과 없음] " + message);
	}

	public void displayBook(Book book) {
		System.out.println(book);
	}

}
