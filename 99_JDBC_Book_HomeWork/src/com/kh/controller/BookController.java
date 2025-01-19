package com.kh.book.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.book.model.dao.BookDao;
import com.kh.book.model.vo.Book;
import com.kh.book.model.vo.Magazine;

public class BookService {

    // 도서 추가
    public int addBook(Book book) {
        Connection conn = getConnection();
        int result = new BookDao().addBook(conn, book);
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
        int result = new BookDao().addMagazine(conn, magazine);
        if (result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);
        return result;
    }

    // 전체 책 조회
    public ArrayList<Book> getAllBook() {
        Connection conn = getConnection();
        ArrayList<Book> list = new BookDao().getAllBook(conn);
        close(conn);
        return list;
    }

    // 일반 도서만 조회
    public ArrayList<Book> onlySearchBook() {
        Connection conn = getConnection();
        ArrayList<Book> list = new BookDao().onlySearchBook(conn);
        close(conn);
        return list;
    }

    // 잡지만 조회
    public ArrayList<Magazine> onlySearchMagazine() {
        Connection conn = getConnection();
        ArrayList<Magazine> list = new BookDao().onlySearchMagazine(conn);
        close(conn);
        return list;
    }

    // bNo로 책 찾기
    public Book searchBookBybNo(String bNo) {
        Connection conn = getConnection();
        Book book = new BookDao().searchBookBybNo(conn, bNo);
        close(conn);
        return book;
    }

    // 책 제목으로 책 찾기
    public ArrayList<Book> searchBookByTitle(String title) {
        Connection conn = getConnection();
        ArrayList<Book> list = new BookDao().searchBookByTitle(conn, title);
        close(conn);
        return list;
    }

    // 출간연도로 잡지 찾기
    public ArrayList<Magazine> magazineOfThisYearInfo(int year) {
        Connection conn = getConnection();
        ArrayList<Magazine> list = new BookDao().magazineOfThisYearInfo(conn, year);
        close(conn);
        return list;
    }

    // 출판사로 책 찾기
    public ArrayList<Book> searchBookByPublisher(String publisher) {
        Connection conn = getConnection();
        ArrayList<Book> list = new BookDao().searchBookByPublisher(conn, publisher);
        close(conn);
        return list;
    }

    // 특정 가격 이하로 책 찾기
    public ArrayList<Book> searchBookByPrice(int price) {
        Connection conn = getConnection();
        ArrayList<Book> list = new BookDao().searchBookByPrice(conn, price);
        close(conn);
        return list;
    }

    // 전체 가격 합계 조회
    public int getTotalPrice() {
        Connection conn = getConnection();
        int totalPrice = new BookDao().getTotalPrice(conn);
        close(conn);
        return totalPrice;
    }

    // 전체 가격 평균 조회
    public double getAvgPrice() {
        Connection conn = getConnection();
        double avgPrice = new BookDao().getAvgPrice(conn);
        close(conn);
        return avgPrice;
    }
}
