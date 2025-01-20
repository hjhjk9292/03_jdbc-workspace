package com.kh.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.vo.Book;
import com.kh.model.vo.Magazine;
import static com.kh.common.JDBCTemplate.*;

public class BookDao {

	// 도서 추가
	public int addBook(Connection conn, Book book) {
		String sql = "INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION)"
				+ " VALUES (TO_CHAR(SEQ_BNO.NEXTVAL), ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublisher());
			pstmt.setInt(4, book.getPrice());
			pstmt.setString(5, book.getDescription());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 잡지 추가
	public int addMagazine(Connection conn, Magazine magazine) {
		String sql = "INSERT INTO MAGAZINE (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION, YEAR, MONTH)"
				+ " VALUES (TO_CHAR(SEQ_BNO.CURRVAL), ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, magazine.getTitle());
			pstmt.setString(2, magazine.getAuthor());
			pstmt.setString(3, magazine.getPublisher());
			pstmt.setInt(4, magazine.getPrice());
			pstmt.setString(5, magazine.getDescription());
			pstmt.setInt(6, magazine.getYear());
			pstmt.setInt(7, magazine.getMonth());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 전체 도서 조회
	public ArrayList<Book> getAllBooks(Connection conn) {
		String sql = "SELECT * FROM BOOK";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> bookList = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Book book = new Book(rset.getString("BNO"), rset.getString("TITLE"), rset.getString("AUTHOR"),
						rset.getString("PUBLISHER"), rset.getInt("PRICE"), rset.getString("DESCRIPTION"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset); // ResultSet 자원 닫기
			close(pstmt); // PreparedStatement 자원 닫기
		}
		return bookList;
	}

	// 일반 도서만 조회
	public ArrayList<Book> onlySearchBooks(Connection conn) {
		String sql = "SELECT * FROM BOOK WHERE BNO NOT IN (SELECT BNO FROM MAGAZINE)";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> bookList = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Book book = new Book(rset.getString("BNO"), rset.getString("TITLE"), rset.getString("AUTHOR"),
						rset.getString("PUBLISHER"), rset.getInt("PRICE"), rset.getString("DESCRIPTION"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return bookList;
	}

	// 잡지만 조회
	public ArrayList<Magazine> onlySearchMagazines(Connection conn) {
		String sql = "SELECT * FROM MAGAZINE";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Magazine> magazineList = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Magazine magazine = new Magazine(rset.getString("BNO"), rset.getString("TITLE"),
						rset.getString("AUTHOR"), rset.getString("PUBLISHER"), rset.getInt("PRICE"),
						rset.getString("DESCRIPTION"), rset.getInt("YEAR"), rset.getInt("MONTH"));
				magazineList.add(magazine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return magazineList;
	}

	// 도서 번호로 검색
	public Book searchBookBybNo(Connection conn, String bNo) {
		String sql = "SELECT * FROM BOOK WHERE BNO = ?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Book book = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				book = new Book(rset.getString("BNO"), rset.getString("TITLE"), rset.getString("AUTHOR"),
						rset.getString("PUBLISHER"), rset.getInt("PRICE"), rset.getString("DESCRIPTION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return book;
	}

	// 도서 제목으로 검색
	public ArrayList<Book> searchBookByTitle(Connection conn, String title) {
		String sql = "SELECT * FROM BOOK WHERE TITLE LIKE ?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> bookList = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + title + "%");
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Book book = new Book(rset.getString("BNO"), rset.getString("TITLE"), rset.getString("AUTHOR"),
						rset.getString("PUBLISHER"), rset.getInt("PRICE"), rset.getString("DESCRIPTION"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return bookList;
	}

	// 특정 연도의 잡지 조회
	public ArrayList<Magazine> magazineOfThisYearInfo(Connection conn, int year) {
		String sql = "SELECT * FROM MAGAZINE WHERE YEAR = ?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Magazine> magazineList = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, year);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Magazine magazine = new Magazine(rset.getString("BNO"), rset.getString("TITLE"),
						rset.getString("AUTHOR"), rset.getString("PUBLISHER"), rset.getInt("PRICE"),
						rset.getString("DESCRIPTION"), rset.getInt("YEAR"), rset.getInt("MONTH"));
				magazineList.add(magazine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return magazineList;
	}

	// 출판사로 도서 검색
	public ArrayList<Book> searchBookByPublisher(Connection conn, String publisher) {
		String sql = "SELECT * FROM BOOK WHERE PUBLISHER LIKE ?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> bookList = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + publisher + "%");
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Book book = new Book(rset.getString("BNO"), rset.getString("TITLE"), rset.getString("AUTHOR"),
						rset.getString("PUBLISHER"), rset.getInt("PRICE"), rset.getString("DESCRIPTION"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return bookList;
	}

	// 특정 가격 이하의 도서 검색
	public ArrayList<Book> searchBookByPrice(Connection conn, int price) {
		String sql = "SELECT * FROM BOOK WHERE PRICE <= ?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> bookList = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, price);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Book book = new Book(rset.getString("BNO"), rset.getString("TITLE"), rset.getString("AUTHOR"),
						rset.getString("PUBLISHER"), rset.getInt("PRICE"), rset.getString("DESCRIPTION"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return bookList;
	}

	// 도서 가격 합계 조회
	public int getTotalPrice(Connection conn) {
		String sql = "SELECT SUM(PRICE) AS TOTAL FROM BOOK";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalPrice = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				totalPrice = rset.getInt("TOTAL");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalPrice;
	}

	// 도서 가격 평균 조회
	public double getAvgPrice(Connection conn) {
		String sql = "SELECT AVG(PRICE) AS AVERAGE FROM BOOK";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		double avgPrice = 0.0;

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				avgPrice = rset.getDouble("AVERAGE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return avgPrice;
	}
}
