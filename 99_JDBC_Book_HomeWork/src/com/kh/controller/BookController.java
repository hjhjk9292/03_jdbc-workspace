package com.kh.controller;

import java.util.ArrayList;

import com.kh.controller.BookService;
import com.kh.model.vo.Book;
import com.kh.model.vo.Magazine;
import com.kh.view.BookMenu;

public class BookController {

	// 전체 도서 조회 요청 처리
	public void getAllBooks() {
		ArrayList<Book> bookList = new BookService().getAllBooks();

		if (bookList.isEmpty()) {
			new BookMenu().displayNoData("도서관에 소장된 책이 없습니다.");
		} else {
			new BookMenu().displayBookList(bookList);
		}
	}

	// 일반 도서만 조회 요청 처리
	public void onlySearchBooks() {
		ArrayList<Book> bookList = new BookService().onlySearchBooks();

		if (bookList.isEmpty()) {
			new BookMenu().displayNoData("일반 도서가 없습니다.");
		} else {
			new BookMenu().displayBookList(bookList);
		}
	}

	// 잡지만 조회 요청 처리
	public void onlySearchMagazines() {
		ArrayList<Magazine> magazineList = new BookService().onlySearchMagazines();

		if (magazineList.isEmpty()) {
			new BookMenu().displayNoData("잡지가 없습니다.");
		} else {
			new BookMenu().displayMagazineList(magazineList);
		}
	}

	// 도서 추가 요청 처리
	public void addBook(Book book) {
		int result = new BookService().addBook(book);

		if (result > 0) {
			new BookMenu().displaySuccess("도서가 성공적으로 추가되었습니다.");
		} else {
			new BookMenu().displayFail("도서 추가에 실패했습니다. 중복된 번호일 수 있습니다.");
		}
	}

	// 잡지 추가 요청 처리
	public void addMagazine(Magazine magazine) {
		int result = new BookService().addMagazine(magazine);

		if (result > 0) {
			new BookMenu().displaySuccess("잡지가 성공적으로 추가되었습니다.");
		} else {
			new BookMenu().displayFail("잡지 추가에 실패했습니다. 중복된 번호일 수 있습니다.");
		}
	}

	// 도서 번호로 검색 요청 처리
	public void searchBookBybNo(String bNo) {
		Book book = new BookService().searchBookBybNo(bNo);

		if (book == null) {
			new BookMenu().displayNoData(bNo + "에 해당하는 도서를 찾을 수 없습니다.");
		} else {
			new BookMenu().displayBook(book);
		}
	}

	// 도서 제목으로 검색 요청 처리
	public void searchBookByTitle(String title) {
		ArrayList<Book> bookList = new BookService().searchBookByTitle(title);

		if (bookList.isEmpty()) {
			new BookMenu().displayNoData("'" + title + "'에 해당하는 도서를 찾을 수 없습니다.");
		} else {
			new BookMenu().displayBookList(bookList);
		}
	}

	// 특정 연도의 잡지 조회 요청 처리
	public void magazineOfThisYearInfo(int year) {
		ArrayList<Magazine> magazineList = new BookService().magazineOfThisYearInfo(year);

		if (magazineList.isEmpty()) {
			new BookMenu().displayNoData(year + "년도에 해당하는 잡지가 없습니다.");
		} else {
			new BookMenu().displayMagazineList(magazineList);
		}
	}

	// 출판사로 도서 검색 요청 처리
	public void searchBookByPublisher(String publisher) {
		ArrayList<Book> bookList = new BookService().searchBookByPublisher(publisher);

		if (bookList.isEmpty()) {
			new BookMenu().displayNoData("'" + publisher + "'에 해당하는 도서를 찾을 수 없습니다.");
		} else {
			new BookMenu().displayBookList(bookList);
		}
	}

	// 특정 가격 이하의 도서 검색 요청 처리
	public void searchBookByPrice(int price) {
		ArrayList<Book> bookList = new BookService().searchBookByPrice(price);

		if (bookList.isEmpty()) {
			new BookMenu().displayNoData(price + "원 이하에 해당하는 도서가 없습니다.");
		} else {
			new BookMenu().displayBookList(bookList);
		}
	}

	// 도서 가격 합계 및 평균 요청 처리
	public void getTotalAndAvgPrice() {
		BookService service = new BookService();
		int total = service.getTotalPrice();
		double avg = service.getAvgPrice();

		if (total == 0) {
			new BookMenu().displayNoData("도서관에 등록된 도서가 없습니다.");
		} else {
			new BookMenu().displayTotalAndAvgPrice(total, avg);
		}
	}
}
