package com.kh.controller;

import java.util.ArrayList;
import com.kh.model.vo.Book;
import com.kh.model.vo.Magazine;
import com.kh.controller.BookService;

public class BookController {

	private BookService bs = new BookService();

	// 전체 도서 조회
	public ArrayList<Book> getAllBook() {
		return bs.getAllBooks();
	}

	// 일반 도서만 조회
	public ArrayList<Book> onlySearchBook() {
		return bs.onlySearchBooks();
	}

	// 잡지만 조회
	public ArrayList<Magazine> onlySearchMagazine() {
		return bs.onlySearchMagazines();
	}

	// 도서 추가
	public boolean addBook(Book book) {
	    return bs.addBook(book) > 0; // 서비스 호출
	}

	public boolean addBook(Magazine magazine) {
		return bs.addMagazine(magazine) > 0;
	}

	// 도서 번호로 조회
	public Book searchBookBybNo(String bNo) {
		return bs.searchBookBybNo(bNo);
	}

	// 도서 제목으로 조회
	public ArrayList<Book> searchBookByTitle(String title) {
		return bs.searchBookByTitle(title);
	}

	// 특정 연도의 잡지 조회
	public ArrayList<Magazine> magazineOfThisYearInfo(int year) {
		return bs.magazineOfThisYearInfo(year);
	}

	// 출판사로 도서 조회
	public ArrayList<Book> searchBookByPublisher(String publisher) {
		return bs.searchBookByPublisher(publisher);
	}

	// 특정 가격 이하의 도서 조회
	public ArrayList<Book> searchBookByPrice(int price) {
		return bs.searchBookByPrice(price);
	}

	// 도서 가격 합계 및 평균 조회
	public int getTotalPrice() {
		return bs.getTotalPrice();
	}

	public double getAvgPrice() {
		return bs.getAvgPrice();
	}
}
