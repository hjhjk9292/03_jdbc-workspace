package com.kh.book.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

import com.kh.book.model.vo.Book;
import com.kh.book.model.vo.Magazine;

public class BookDao {

    // 도서 추가
    public int addBook(Connection conn, Book book) {
        String sql = "INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION) VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0; // 초기값 설정
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getbNo());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.setString(4, book.getPublisher());
            pstmt.setInt(5, book.getPrice());
            pstmt.setString(6, book.getDescription());
            result = pstmt.executeUpdate(); // 실행된 결과값을 result에 저장
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result; // 실행 결과 반환
    }

    // 잡지 추가
    public int addMagazine(Connection conn, Magazine magazine) {
        String sql = "INSERT INTO MAGAZINE (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION, YEAR, MONTH) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0; // 초기값 설정
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, magazine.getbNo());
            pstmt.setString(2, magazine.getTitle());
            pstmt.setString(3, magazine.getAuthor());
            pstmt.setString(4, magazine.getPublisher());
            pstmt.setInt(5, magazine.getPrice());
            pstmt.setString(6, magazine.getDescription());
            pstmt.setInt(7, magazine.getYear());
            pstmt.setInt(8, magazine.getMonth());
            result = pstmt.executeUpdate(); // 실행된 결과값을 result에 저장
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result; // 실행 결과 반환
    }

    // bNo로 책 찾기
    public Book searchBookBybNo(Connection conn, String bNo) {
        String sql = "SELECT * FROM BOOK WHERE BNO = ?";
        Book book = null; // Book 객체 초기화
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bNo);
            try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    book = new Book(
                            rset.getString("BNO"),
                            rset.getString("TITLE"),
                            rset.getString("AUTHOR"),
                            rset.getString("PUBLISHER"),
                            rset.getInt("PRICE"),
                            rset.getString("DESCRIPTION")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book; // 찾은 책이 없으면 null 반환
    }

    // 전체 가격 합계 조회
    public int getTotalPrice(Connection conn) {
        String sql = "SELECT SUM(PRICE) AS TOTAL_PRICE FROM BOOK";
        int result = 0; // 초기값 설정
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rset = pstmt.executeQuery()) {
            if (rset.next()) {
                result = rset.getInt("TOTAL_PRICE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result; // 합계 반환
    }

    // 전체 가격 평균 조회
    public double getAvgPrice(Connection conn) {
        String sql = "SELECT AVG(PRICE) AS AVG_PRICE FROM BOOK";
        double avgPrice = 0.0; // 초기값 설정
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rset = pstmt.executeQuery()) {
            if (rset.next()) {
                avgPrice = rset.getDouble("AVG_PRICE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avgPrice; // 평균 가격 반환
    }

    // 도서 목록 조회
    public ArrayList<Book> getAllBook(Connection conn) {
        String sql = "SELECT * FROM BOOK";
        ArrayList<Book> bookList = new ArrayList<>(); // ArrayList 초기화
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rset = pstmt.executeQuery()) {
            while (rset.next()) {
                bookList.add(new Book(
                        rset.getString("BNO"),
                        rset.getString("TITLE"),
                        rset.getString("AUTHOR"),
                        rset.getString("PUBLISHER"),
                        rset.getInt("PRICE"),
                        rset.getString("DESCRIPTION")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList; // 도서 목록 반환
    }

    // 일반 도서만 조회
    public ArrayList<Book> onlySearchBook(Connection conn) {
        String sql = "SELECT * FROM BOOK WHERE TYPE = 'BOOK'";
        ArrayList<Book> bookList = new ArrayList<>(); // ArrayList 초기화
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rset = pstmt.executeQuery()) {
            while (rset.next()) {
                bookList.add(new Book(
                        rset.getString("BNO"),
                        rset.getString("TITLE"),
                        rset.getString("AUTHOR"),
                        rset.getString("PUBLISHER"),
                        rset.getInt("PRICE"),
                        rset.getString("DESCRIPTION")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList; // 일반 도서 목록 반환
    }

    // 잡지만 조회
    public ArrayList<Magazine> onlySearchMagazine(Connection conn) {
        String sql = "SELECT * FROM MAGAZINE";
        ArrayList<Magazine> magazineList = new ArrayList<>(); // ArrayList 초기화
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rset = pstmt.executeQuery()) {
            while (rset.next()) {
                magazineList.add(new Magazine(
                        rset.getString("BNO"),
                        rset.getString("TITLE"),
                        rset.getString("AUTHOR"),
                        rset.getString("PUBLISHER"),
                        rset.getInt("PRICE"),
                        rset.getString("DESCRIPTION"),
                        rset.getInt("YEAR"),
                        rset.getInt("MONTH")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return magazineList; // 잡지 목록 반환
    }
}
