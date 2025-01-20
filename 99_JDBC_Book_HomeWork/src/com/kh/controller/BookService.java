package com.kh.controller;

import java.sql.Connection;
import java.util.ArrayList;
import com.kh.model.dao.BookDao;
import com.kh.model.vo.Book;
import com.kh.model.vo.Magazine;
import static com.kh.common.JDBCTemplate.*;

public class BookService {

	private BookDao bd = new BookDao();

	// 전체 도서 조회
	public ArrayList<Book> getAllBooks() {
		Connection conn = getConnection();
		ArrayList<Book> result = bd.getAllBooks(conn);
		close(conn);
		return result;
	}

	// 일반 도서만 조회
	public ArrayList<Book> onlySearchBooks() {
		Connection conn = getConnection();
		ArrayList<Book> result = bd.onlySearchBooks(conn);
		close(conn);
		return result;
	}

	// 잡지만 조회
	public ArrayList<Magazine> onlySearchMagazines() {
		Connection conn = getConnection();
		ArrayList<Magazine> result = bd.onlySearchMagazines(conn);
		close(conn);
		return result;
	}

	// 도서 추가
	public int addBook(Book book) {
		Connection conn = getConnection();
		int result = bd.addBook(conn, book);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	// 잡지 추가
	public int addMagazine(Magazine magazine) {
		Connection conn = getConnection();
		int result = bd.addMagazine(conn, magazine);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	// 도서 번호로 조회
	public Book searchBookBybNo(String bNo) {
		Connection conn = getConnection();
		Book result = bd.searchBookBybNo(conn, bNo);
		close(conn);
		return result;
	}

	// 도서 제목으로 조회
	public ArrayList<Book> searchBookByTitle(String title) {
		Connection conn = getConnection();
		ArrayList<Book> result = bd.searchBookByTitle(conn, title);
		close(conn);
		return result;
	}

	// 특정 연도의 잡지 조회
	public ArrayList<Magazine> magazineOfThisYearInfo(int year) {
		Connection conn = getConnection();
		ArrayList<Magazine> result = bd.magazineOfThisYearInfo(conn, year);
		close(conn);
		return result;
	}

	// 출판사로 도서 조회
	public ArrayList<Book> searchBookByPublisher(String publisher) {
		Connection conn = getConnection();
		ArrayList<Book> result = bd.searchBookByPublisher(conn, publisher);
		close(conn);
		return result;
	}

	// 특정 가격 이하의 도서 조회
	public ArrayList<Book> searchBookByPrice(int price) {
		Connection conn = getConnection();
		ArrayList<Book> result = bd.searchBookByPrice(conn, price);
		close(conn);
		return result;
	}

	// 도서 가격 합계 조회
	public int getTotalPrice() {
		Connection conn = getConnection();
		int result = bd.getTotalPrice(conn);
		close(conn);
		return result;
	}

	// 도서 가격 평균 조회
	public double getAvgPrice() {
		Connection conn = getConnection();
		double result = bd.getAvgPrice(conn);
		close(conn);
		return result;
	}
}
